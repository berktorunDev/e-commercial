package com.app.ecommercial.util.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GlobalResponseHandler {

    public static ResponseEntity<Object> successResponse(HttpStatus status, String infoMessage, Object data) {
        Map<String, Object> result = new HashMap<>();

        result.put("success", Boolean.TRUE);
        result.put("data", data);
        result.put("infoMessage", infoMessage);
        if (data instanceof List<?>) {
            List<?> dataList = (List<?>) data;
            result.put("dataCount", dataList.size());
        }

        return new ResponseEntity<>(result, status);
    }

    public static ResponseEntity<Object> errorResponse(HttpStatus status, String errorMessage) {
        // Create a map containing the properties of the error response
        Map<String, Object> result = new HashMap<>();
        result.put("success", Boolean.FALSE);
        result.put("errorMessage", errorMessage);

        // Create and return the ResponseEntity
        return new ResponseEntity<>(result, status);
    }
}
