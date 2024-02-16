package com.app.ecommercial.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommercial.exception.InvalidCredentials;
import com.app.ecommercial.model.dto.request.AuthenticationRequestDTO;
import com.app.ecommercial.model.dto.request.LoginRequestDTO;
import com.app.ecommercial.model.dto.request.RegisterRequestDTO;
import com.app.ecommercial.model.dto.request.ResetPasswordRequestDTO;
import com.app.ecommercial.model.dto.request.SetPasswordRequestDTO;
import com.app.ecommercial.model.entity.User;
import com.app.ecommercial.service.AuthService;
import com.app.ecommercial.util.handler.GlobalResponseHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequestDTO request) {
        var response = authService.authenticate(request.getUsername(), request.getPassword());
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User authenticated successfully", response);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDTO request) {
        var response = authService.register(request.getUsername(), request.getEmail(), request.getPassword(),
                request.getFirstName(), request.getLastName(), request.getPhone(), false);
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User registered successfully", response);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO request) throws InvalidCredentials {
        var response = authService.login(request.getUsername(), request.getPassword());
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User login success", response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        var response = authService.logout(request.getHeader("Authorization"));
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User logged out successfully", response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(ResetPasswordRequestDTO request) {
        var response = authService.resetPassword(request.getEmail());
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User reset password successfully", response);
    }

    @PostMapping("/set-password")
    public ResponseEntity<Object> setPassword(@RequestBody SetPasswordRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        var response = authService.setPassword(userId, request.getNewPassword());
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User set new password successfully", response);
    }
}
