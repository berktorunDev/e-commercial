package com.app.ecommercial.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnnouncementRequestDTO {
    private String title;
    private String description;
    private Long categoryId;
    private double budget;
    private String phone;
    private boolean isEnable;
}
