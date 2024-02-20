package com.app.ecommercial.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAnnouncementRequestDTO {
    private String title;
    private String description;
    private Long categoryId;
    private Double budget;
    private String contactInfo;
    private Boolean isEnable;
}
