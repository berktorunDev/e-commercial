package com.app.ecommercial.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.app.ecommercial.config.jwt.JwtService;
import com.app.ecommercial.exception.InvalidCredentials;
import com.app.ecommercial.model.dto.response.AuthenticationResponseDTO;
import com.app.ecommercial.model.entity.User;
import com.app.ecommercial.repository.UserRepository;
import com.app.ecommercial.util.ApplicationUtil;
import com.app.ecommercial.util.dictionary.ExceptionDictionary;
import com.app.ecommercial.util.dictionary.ResponseDictionary;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;
    private final ApplicationUtil otpGenerator;
    private final EmailService emailService;

    @Value("${application.otp.expiration}")
    private int otpExpiration;

    public AuthenticationResponseDTO authenticate(String username, String password) {
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            var user = userRepository.findByUsername(username).orElseThrow();
            return AuthenticationResponseDTO.builder().accessToken(jwtService.generateToken(user)).build();
        }
        throw new UsernameNotFoundException("invalid username {} " + username);
    }

    public String register(String username, String email, String password, String firstName,
            String lastName, String phone, boolean isVerified) {
        var encodedPassword = passwordEncoder.encode(password);

        var user = User.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .isVerified(isVerified)
                .build();

        var savedUser = userRepository.save(user);

        return ObjectUtils.isEmpty(savedUser) ? ResponseDictionary.UserRegistrationFail
                : ResponseDictionary.UserRegistrationSuccess;

    }

    public AuthenticationResponseDTO login(String username, String password) throws InvalidCredentials {
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            var user = userRepository.findByUsername(username).orElseThrow();
            user.setLastLoginDate(LocalDateTime.now());
            userRepository.save(user);
            return AuthenticationResponseDTO.builder().accessToken(jwtService.generateToken(user)).build();
        }
        throw new InvalidCredentials();
    }

    public String logout(String requestHeader) {
        try {
            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                var token = requestHeader.substring(7); // Remove "Bearer " prefix
                var userId = jwtService.extractUserId(token);
                var user = userRepository.findById(UUID.fromString(userId)).orElseThrow();
                user.setLastLogoutDate(LocalDateTime.now());
                userRepository.save(user);

                // Calculate remaining time of the token
                long expirationTime = jwtService.extractExpiration(token).getTime(); // Get expiration time from token
                long currentTime = System.currentTimeMillis();
                long ttl = expirationTime - currentTime; // Remaining time in milliseconds

                if (ttl > 0) {
                    // Blacklist the token in Redis with the remaining TTL
                    redisService.setValue(token, "BLACK_LISTED", ttl);
                }
                SecurityContextHolder.clearContext();
            }
            return ResponseDictionary.LogoutSuccess;
        } catch (Exception e) {
            return ResponseDictionary.LogoutFail;
        }
    }

    public String resetPassword(String email) {
        try {
            var user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException(ExceptionDictionary.UserNotFound));

            var otp = otpGenerator.generateOtp();
            var message = "Verification code: " + otp;
            emailService.sendSimpleMessage(user.getEmail(), "VERIFICATION CODE", message);

            user.setVerificationCode(otp);
            user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(otpExpiration));
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);

            return ResponseDictionary.VerificationCodeSendingSuccess;
        } catch (Exception e) {
            return ResponseDictionary.VerificationCodeSendingFail;
        }
    }

    public String setPassword(UUID userId, String newPassword) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);

        if (!pattern.matcher(newPassword).matches()) {
            return ResponseDictionary.PasswordCriteriaFail;
        }

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionDictionary.UserNotFound));

        if (user.getPreviousPasswords() != null) {
            for (String prevPassword : user.getPreviousPasswords()) {
                if (passwordEncoder.matches(newPassword, prevPassword)) {
                    return ResponseDictionary.PreviousPasswordUsing;
                }
            }
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);

        if (user.getPreviousPasswords() == null) {
            user.setPreviousPasswords(new ArrayList<>());
        }
        user.getPreviousPasswords().add(encodedNewPassword);
        if (user.getPreviousPasswords().size() > 3) {
            user.getPreviousPasswords().remove(0);
        }
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseDictionary.PasswordUpdatedSuccess;
    }
}
