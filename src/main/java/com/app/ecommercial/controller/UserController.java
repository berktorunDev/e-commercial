package com.app.ecommercial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommercial.config.jwt.JwtService;
import com.app.ecommercial.service.UserService;
import com.app.ecommercial.util.handler.GlobalResponseHandler;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping()
    public ResponseEntity<Object> getUserById(@RequestHeader("Authorization") String token) {
        String userId = jwtService.extractUserId(token.substring(7)); // Remove 'Bearer'
        var response = userService.getById(UUID.fromString(userId));
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User fetched successfully", response);
    }

}
