package com.example.demo.presentation.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.buisness.dtos.requestDTOs.CategoryRequestDto;
import com.example.demo.buisness.services.interfaces.CategoriesService;
import com.example.demo.presentation.mappers.CategoryMapper;
import com.example.demo.presentation.responseViewModel.CategoryResponse;
import com.example.demo.repository.entities.Category;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class CategoriesController {

    
    private final CategoriesService categoriesService;
    private final CategoryMapper categoryMapper;

    // @GetMapping("/categories")

    // public List<Category> getAllCategries() {

    //     return categoriesService.getAllCategories();
    // }

    @GetMapping("/categories")
    public CategoryResponse getCategoriesPaged(@RequestParam("start") int start,@RequestParam("size") int size){

        return new CategoryResponse("succecful ",categoriesService.getAllCategoriesPaged(start, size));

    }


    @PostMapping("/categories")
    public CategoryResponse insertCategory(@RequestBody CategoryRequestDto category){
        return categoriesService.insertCategory(categoryMapper.fromDtoToEntity(category));
    }

//    @GetMapping("/{id}")
//    public Optional <Category> getCategorybyid(@PathVariable int id){
//        return categoriesService.getByID(id);
//    }

    @GetMapping("/categories/{categoryName}")
    public Category getCategorybyid(@PathVariable String categoryName){
        return categoriesService.getCategoryByName(categoryName);
    }

    @DeleteMapping("/categories/{id}")
    public CategoryResponse insertCategory(@PathVariable int id){

        return categoriesService.delete(id);
    }


}
