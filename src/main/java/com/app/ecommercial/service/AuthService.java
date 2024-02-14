package com.app.ecommercial.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import com.app.ecommercial.util.dictionary.ResponseDictionary;
import com.app.ecommercial.util.handler.GlobalResponseHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO authenticate(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            var user = userRepository.findByUsername(username).orElseThrow();
            return AuthenticationResponseDTO.builder().accessToken(jwtService.generateToken(user)).build();
        }
        throw new UsernameNotFoundException("invalid username {} " + username);
    }

    public String register(String username, String email, String password, String firstName,
            String lastName, String phone, String accountType, boolean isVerified) {
        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .accountType(accountType)
                .isVerified(isVerified)
                .build();

        User savedUser = userRepository.save(user);

        return ObjectUtils.isEmpty(savedUser) ? ResponseDictionary.UserRegistrationFail
                : ResponseDictionary.UserRegistrationSuccess;

    }

    public AuthenticationResponseDTO login(String username, String password) throws InvalidCredentials {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            var user = userRepository.findByUsername(username).orElseThrow();
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            return AuthenticationResponseDTO.builder().accessToken(jwtService.generateToken(user)).build();
        }
        throw new InvalidCredentials();
    }

    public String logout(String requestHeader) {
        try {
            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                String token = requestHeader.substring(7); // Remove "Bearer " prefix
                var userId = jwtService.extractUserId(token);
                var user = userRepository.findById(UUID.fromString(userId)).orElseThrow();
                user.setLastLogoutDate(new Date());
                userRepository.save(user);
                SecurityContextHolder.clearContext();
            }
            return ResponseDictionary.LogoutSuccess;
        } catch (Exception e) {
            return ResponseDictionary.LogoutFail;
        }
    }
}
