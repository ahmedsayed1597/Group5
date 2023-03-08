package com.example.demo.presentation.mappers;

import org.mapstruct.Mapper;

import com.example.demo.buisness.dtos.responseDTOs.ProductResponseDto;
import com.example.demo.repository.entities.Product;
@Mapper(componentModel = "spring")
public interface ProductResponseMapper {
    
    ProductResponseDto fromEntityToDto(Product product);

    Product fromDtoToEntity(ProductResponseDto product);

}
