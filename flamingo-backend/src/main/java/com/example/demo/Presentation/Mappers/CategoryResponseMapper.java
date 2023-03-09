package com.example.demo.presentation.mappers;


import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.buisness.dtos.responseDTOs.CategoryResponseDto;
import com.example.demo.repository.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {
    
    CategoryResponseDto fromEntityToDto(Category category);
    List<CategoryResponseDto> fromEntityToDto(List<Category> category);

    Category fromDtoToEntity(CategoryResponseDto category);

}
