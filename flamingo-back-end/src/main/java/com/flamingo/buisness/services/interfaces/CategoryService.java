package com.flamingo.buisness.services.interfaces;


import com.flamingo.persistence.entities.Category;
import com.flamingo.presentation.dto.CategoryDto;
import com.flamingo.presentation.responseviewmodel.CategoryResponse;


public interface CategoryService {
    
    CategoryDto createCategory(Category category);

	CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	CategoryDto updateCategory(Category category, Long categoryId);

	String deleteCategory(Long categoryId);
}
