package com.example.demo.presentation.mappers;

import com.example.demo.buisness.dtos.requestDTOs.ProductRequestDto;
import com.example.demo.repository.entities.Product;

public interface ProductMapper {

    ProductRequestDto fromEntityToDto(Product Product);

    Product fromDtoToEntity(ProductRequestDto Product);

    
}
