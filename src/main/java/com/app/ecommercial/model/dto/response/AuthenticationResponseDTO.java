package com.app.ecommercial.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDTO {

    @JsonProperty("access_token")
    private final String accessToken;
}
