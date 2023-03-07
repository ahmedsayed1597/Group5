package com.example.demo.Presentation.Mappers.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Buisness.DTOs.RequestDTOs.ProductRequestDto;
import com.example.demo.Repository.Dao.interfaces.CategoryDao;
import com.example.demo.Repository.Dao.interfaces.UserDao;
import com.example.demo.Repository.Entities.Product;

public class ProductMapperImpl {
    @Autowired
    private static CategoryDao categoryDAO;

    public static ProductRequestDto fromEntityToDto(Product product){
        return new ProductRequestDto(product.getName(), product.getDescription(),product.getPrice(),product.getQuantity(),product.getCategory().getId());

    }

    public static Product fromDtoToEntity(ProductRequestDto product){
        
        return new Product(product.getName(), product.getDescription(), product.getPrice(),
        product.getQuantity(),categoryDAO.getById(product.getCategoryid()));
    }

}
