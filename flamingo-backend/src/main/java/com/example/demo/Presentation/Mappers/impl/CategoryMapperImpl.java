package com.example.demo.Presentation.Mappers.impl;

import com.example.demo.Buisness.DTOs.RequestDTOs.CategoryRequestDto;
import com.example.demo.Repository.Entities.Category;

public class CategoryMapperImpl {
    public static CategoryRequestDto fromEntityToDto(Category category){
        return new CategoryRequestDto(category.getImage(), category.getCategoryName());

    }

    public static Category fromDtoToEntity(CategoryRequestDto  category){

        return new Category(category.getImage(), category.getCategoryName());
    }
}
