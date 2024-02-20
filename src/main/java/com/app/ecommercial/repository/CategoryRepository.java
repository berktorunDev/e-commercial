package com.app.ecommercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecommercial.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
