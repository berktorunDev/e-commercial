package com.app.ecommercial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommercial.service.CategoryService;
import com.app.ecommercial.util.handler.GlobalResponseHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllCategories() {
        var categories = categoryService.getAll();
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "Categories retrieved successfully", categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long id) {
        var category = categoryService.getById(id);
        return GlobalResponseHandler.successResponse(HttpStatus.OK, "Category retrieved successfully", category);
    }
}
