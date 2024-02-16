package com.app.ecommercial.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.ecommercial.model.dto.request.UpdateUserRequestDTO;
import com.app.ecommercial.model.entity.User;
import com.app.ecommercial.repository.UserRepository;
import com.app.ecommercial.util.ApplicationUtil;
import com.app.ecommercial.util.dictionary.ExceptionDictionary;
import com.app.ecommercial.util.dictionary.ResponseDictionary;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService<User, UUID> implements UserDetailsService {

    private final UserRepository userRepository;
    private final ApplicationUtil otpGenerator;
    private final EmailService emailService;

    @Value("${application.otp.expiration}")
    private int otpExpiration;

    @Override
    protected JpaRepository<User, UUID> getRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public User updateUser(UUID id, UpdateUserRequestDTO updateDto) {
        var user = getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionDictionary.UserNotFound));

        if (!user.getUsername().equals(updateDto.getUsername())) {
            userRepository.findByUsername(updateDto.getUsername()).ifPresent(u -> {
                throw new DataIntegrityViolationException(ExceptionDictionary.UsernameAlreadyUse);
            });
        }
        if (!user.getEmail().equals(updateDto.getEmail())) {
            userRepository.findByEmail(updateDto.getEmail()).ifPresent(u -> {
                throw new DataIntegrityViolationException(ExceptionDictionary.EmailAlreadyUse);
            });
        }

        user.setUsername(updateDto.getUsername());
        user.setFirstName(updateDto.getFirstName());
        user.setLastName(updateDto.getLastName());
        user.setPhone(updateDto.getPhone());
        user.setEmail(updateDto.getEmail());
        user.setUpdatedAt(LocalDateTime.now());

        return getRepository().save(user);
    }

    public String verifyAccount(UUID userId) {
        try {
            var user = getRepository().findById(userId)
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
}
