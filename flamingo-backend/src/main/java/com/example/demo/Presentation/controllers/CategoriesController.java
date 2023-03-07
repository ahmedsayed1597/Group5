package com.example.demo.Presentation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Buisness.DTOs.RequestDTOs.CategoryRequestDto;
import com.example.demo.Buisness.DTOs.RequestDTOs.UserRequestDto;
import com.example.demo.Buisness.Services.interfaces.CategoriesService;
import com.example.demo.Presentation.Mappers.impl.CategoryMapperImpl;
import com.example.demo.Presentation.ResponseViewModel.CategoryResponse;
import com.example.demo.Repository.Dao.interfaces.CategoryDao;
import com.example.demo.Repository.Entities.Category;


@RestController
@RequestMapping("categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/all")
    public List<Category> getAllCategries() {

        return categoriesService.getAllCategories();
    }

    @PostMapping("/insert")
    public CategoryResponse insertCategory(@RequestBody CategoryRequestDto category){
        return categoriesService.insertCategory(CategoryMapperImpl.fromDtoToEntity(category));
    }

    @GetMapping("/{id}")
    public Optional <Category> getCategorybyid(@PathVariable int id){
        return categoriesService.getByID(id);
    }

    @DeleteMapping("/delete/{id}")
    public CategoryResponse insertCategory(@PathVariable int id){

        return categoriesService.delete(id);
    }
}
