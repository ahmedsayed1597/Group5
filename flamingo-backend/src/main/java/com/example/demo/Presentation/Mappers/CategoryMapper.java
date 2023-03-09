package com.example.demo.presentation.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.buisness.dtos.requestDTOs.CategoryRequestDto;
import com.example.demo.repository.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    
    Category fromDtoToEntity(CategoryRequestDto category);

    CategoryRequestDto fromModelToDto(Category category);
    List<CategoryRequestDto> fromModelToDto(List<Category> category);

}
