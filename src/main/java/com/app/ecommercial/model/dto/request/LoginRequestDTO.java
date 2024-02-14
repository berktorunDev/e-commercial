package com.app.ecommercial.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDTO {

    private final String username;

    private final String password;
}
