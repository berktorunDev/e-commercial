package com.app.ecommercial.model.dto.response;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private Date accountCreationDate;
    private Date accountUpdateDate;
    private String accountType;
    private boolean isVerified;
    private Date lastLoginDate;
    private Date lastLogoutDate;
    private List<String> previousPasswords;
    private List<String> favorites;
}
