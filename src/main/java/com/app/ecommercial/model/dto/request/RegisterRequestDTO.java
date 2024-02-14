package com.app.ecommercial.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDTO {

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private String accountType;

    private boolean isVerified;
}
