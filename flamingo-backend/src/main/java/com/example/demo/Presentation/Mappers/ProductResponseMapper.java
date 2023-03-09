package com.example.demo.presentation.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.buisness.dtos.responseDTOs.ProductResponseDto;
import com.example.demo.repository.entities.Product;
@Mapper(componentModel = "spring")
public interface ProductResponseMapper {
    
    ProductResponseDto fromEntityToDto(Product product);
    List<ProductResponseDto> fromEntityToDto(List<Product> product);

    Product fromDtoToEntity(ProductResponseDto product);

}
