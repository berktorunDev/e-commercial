package com.app.ecommercial.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    public String logout(String token) {
        return jwtService.invalidateToken(token) ? ResponseDictionary.LogoutSuccess : ResponseDictionary.LogoutFail;
    }
}
