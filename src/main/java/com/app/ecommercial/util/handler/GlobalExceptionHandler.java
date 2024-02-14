package com.app.ecommercial.util.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return GlobalResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred: " + ex.getMessage());
    }
}
