package com.app.ecommercial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommercial.exception.InvalidCredentials;
import com.app.ecommercial.model.dto.request.AuthenticationRequestDTO;
import com.app.ecommercial.model.dto.request.LoginRequestDTO;
import com.app.ecommercial.model.dto.request.RegisterRequestDTO;
import com.app.ecommercial.model.dto.response.AuthenticationResponseDTO;
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
        AuthenticationResponseDTO responseDTO = authService.authenticate(request.getUsername(), request.getPassword());
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User authenticated successfully", responseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDTO request) {
        var response = authService.register(request.getUsername(), request.getEmail(), request.getPassword(),
                request.getFirstName(), request.getLastName(), request.getPhone(), request.getAccountType(), false);
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

}
