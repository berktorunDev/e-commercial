package com.app.ecommercial.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "categories", indexes = {
        @Index(columnList = "parent_id", name = "idx_parent_id"),
        @Index(columnList = "name", name = "idx_category_name")
})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(unique = false, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    @JsonManagedReference
    private List<Category> subCategories;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Announcement> announcements;
}
