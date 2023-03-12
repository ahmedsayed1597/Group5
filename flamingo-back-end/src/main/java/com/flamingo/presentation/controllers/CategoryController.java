package com.flamingo.presentation.controllers;

import com.flamingo.presentation.responseviewmodel.CategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.flamingo.presentation.dto.CategoryDto;

import com.flamingo.buisness.services.interfaces.CategoryService;
import com.flamingo.persistence.entities.Category;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

        @PreAuthorize("hasRole('admin')")

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody Category category){

        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "field", defaultValue = "categoryId", required = false) String field,
            @RequestParam(name = "orderBy", defaultValue = "asc", required = false) String orderBy) {

        CategoryResponse categoryResponse = categoryService.getCategories(pageNumber, pageSize, field, orderBy);

        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

        // @PreAuthorize("hasRole('admin')")

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody Category category  ,@PathVariable Long categoryId ){

        return  new ResponseEntity<>(categoryService.updateCategory(category,categoryId),HttpStatus.OK);
    }

        // @PreAuthorize("hasRole('admin')")


    @DeleteMapping ("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId ){

        return  new ResponseEntity<>(categoryService.deleteCategory(categoryId),HttpStatus.NO_CONTENT);
    }
    
}
