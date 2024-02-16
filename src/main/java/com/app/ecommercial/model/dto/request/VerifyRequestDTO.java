package com.app.ecommercial.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyRequestDTO {
    
    private final String email;
    
    private final String otp;
}
