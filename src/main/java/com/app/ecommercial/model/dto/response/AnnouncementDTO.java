package com.app.ecommercial.model.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO {
    private Long id;
    private String title;
    private String description;
    private Long categoryId;
    private double budget;
    private String phone;
    private boolean isEnable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID userId;
}