package com.app.ecommercial.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.app.ecommercial.config.jwt.JwtService;
import com.app.ecommercial.model.dto.response.AuthenticationResponseDTO;
import com.app.ecommercial.repository.UserRepository;
import com.app.ecommercial.util.dictionary.ExceptionDictionary;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthenticationResponseDTO verifyOtp(String email, String otp) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionDictionary.UserNotFound));

        if (user.getVerificationCode().equals(otp)
                && user.getVerificationCodeExpiration().isBefore(LocalDateTime.now())) {
            user.setVerified(true);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return AuthenticationResponseDTO.builder().accessToken(jwtService.generateToken(user)).build();
        }
        return null;
    }
}
