package com.example.demo.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.buisness.dtos.requestDTOs.ProductRequestDto;
import com.example.demo.buisness.services.interfaces.CategoriesService;
import com.example.demo.repository.entities.Category;
import com.example.demo.repository.entities.Product;

@Mapper(componentModel = "spring")
public abstract class ProductMapperAbstract {
    
    @Autowired
    private CategoriesService categoriesService;


    @Mapping(source = "category",target = "categoryid" , qualifiedByName = "getID")
    public abstract ProductRequestDto fromEntityToDto(Product Product);

    @Mapping(source = "categoryid",target = "category" , qualifiedByName = "getCategory")
    public abstract Product fromDtoToEntity(ProductRequestDto Product);


    @Named("getID")
    public int getID(Category category){
        return category.getId();
    }

    @Named("getCategory")
    public Category getByID(int dto){
        return categoriesService.getByID(dto).get();

    }
 
    

}
