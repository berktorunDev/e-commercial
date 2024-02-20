package com.app.ecommercial.model.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.app.ecommercial.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", indexes = {
        @Index(columnList = "username", name = "idx_username"),
        @Index(columnList = "email", name = "idx_email"),
        @Index(columnList = "lastLoginDate", name = "idx_lastLoginDate"),
        @Index(columnList = "lastLogoutDate", name = "idx_lastLogoutDate"),
        @Index(columnList = "createdAt", name = "idx_user_createdAt"),
        @Index(columnList = "updatedAt", name = "idx_user_updatedAt")
})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private UUID id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private boolean isVerified;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;

    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "user")
    private List<Announcement> createdAnnouncements;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_favorites", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "announcement_id")
    private Set<Long> favorites = new HashSet<>();

    @Transient
    private List<String> previousPasswords;

    private boolean accountNonExpired;

    private boolean isEnabled;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private LocalDateTime lastLoginDate;

    private LocalDateTime lastLogoutDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String verificationCode;

    private LocalDateTime verificationCodeExpiration;
}
