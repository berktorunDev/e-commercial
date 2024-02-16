package com.app.ecommercial.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequestDTO {

    private final String username;
    
    private final String firstName;

    private final String lastName;

    private final String phone;

    private final String email;
}
