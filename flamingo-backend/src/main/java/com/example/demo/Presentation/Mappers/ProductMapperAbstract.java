package com.example.demo.presentation.mappers;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.buisness.dtos.requestDTOs.ProductRequestDto;
import com.example.demo.buisness.services.interfaces.CategoriesService;
import com.example.demo.repository.entities.Product;

@Mapper
public abstract class ProductMapperAbstract{
    
    @Autowired
    private CategoriesService categoriesService;

    @Autowired 
    private ProductMapper productMapper;

    public ProductRequestDto fromEntityToDto(Product product){
        ProductRequestDto dto = productMapper.fromEntityToDto(product);
        dto.setCategoryid(product.getCategory().getId());
        return dto;

    }
    public Product fromDtoToEntity(ProductRequestDto dto){
        Product product = productMapper.fromDtoToEntity(dto);
        product.setCategory(categoriesService.getByID(dto.getCategoryid()).get());
        return product;

    }
 
    

}
