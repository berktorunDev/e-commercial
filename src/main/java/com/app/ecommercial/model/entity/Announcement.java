package com.app.ecommercial.model.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "announcements", indexes = {
        @Index(columnList = "user_id", name = "idx_user_id"),
        @Index(columnList = "category_id", name = "idx_category_id"),
        @Index(columnList = "title", name = "idx_title"),
        @Index(columnList = "createdAt", name = "idx_createdAt"),
        @Index(columnList = "updatedAt", name = "idx_updatedAt")
})
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private double budget;

    @Column(name = "phone")
    private String phone;

    private boolean isEnable;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
