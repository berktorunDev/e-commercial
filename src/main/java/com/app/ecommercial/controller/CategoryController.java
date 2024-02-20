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
        try {
            var categories = categoryService.getAll();
            return GlobalResponseHandler.successResponse(HttpStatus.OK, "Categories retrieved successfully",
                    categories);
        } catch (Exception e) {
            return GlobalResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long id) {
        try {
            var category = categoryService.getById(id);
            return GlobalResponseHandler.successResponse(HttpStatus.OK, "Category retrieved successfully", category);
        } catch (Exception e) {
            return GlobalResponseHandler.errorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
