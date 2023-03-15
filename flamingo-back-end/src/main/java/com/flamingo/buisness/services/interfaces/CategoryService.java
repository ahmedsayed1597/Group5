package com.flamingo.buisness.services.interfaces;


import com.flamingo.persistence.entities.Category;
import com.flamingo.presentation.dto.CategoryDTO;
import com.flamingo.presentation.responseviewmodel.CategoryResponse;


public interface CategoryService {
    
    CategoryDTO createCategory(Category category);

	CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	CategoryDTO updateCategory(Category category, Long categoryId);

	String deleteCategory(Long categoryId);

    CategoryDTO getCategoryById(Long categoryId);
}
