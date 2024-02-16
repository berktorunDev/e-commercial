package com.app.ecommercial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommercial.model.dto.request.UpdateUserRequestDTO;
import com.app.ecommercial.model.entity.User;
import com.app.ecommercial.service.UserService;
import com.app.ecommercial.util.handler.GlobalResponseHandler;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<Object> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        var response = userService.getById(userId);
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User fetched successfully", response);
    }

    @PutMapping()
    public ResponseEntity<Object> updateUser(@RequestBody UpdateUserRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        var response = userService.updateUser(userId, request);
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User updated successfully", response);
    }

    @GetMapping("/verify-account")
    public ResponseEntity<Object> verifyAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        var response = userService.verifyAccount(userId);
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "User updated successfully", response);
    }
}
