package com.app.ecommercial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommercial.model.dto.request.VerifyRequestDTO;
import com.app.ecommercial.model.dto.response.AuthenticationResponseDTO;
import com.app.ecommercial.service.VerificationService;
import com.app.ecommercial.util.handler.GlobalResponseHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/verify")
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping()
    public ResponseEntity<Object> verify(@RequestBody VerifyRequestDTO request) {
        AuthenticationResponseDTO responseDTO = verificationService.verifyOtp(request.getEmail(), request.getOtp());
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "OTP verified successfully", responseDTO);
    }
}
