package com.app.ecommercial.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
