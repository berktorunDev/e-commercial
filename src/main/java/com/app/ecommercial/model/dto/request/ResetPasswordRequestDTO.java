package com.app.ecommercial.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordRequestDTO {
    private final String email;
}
