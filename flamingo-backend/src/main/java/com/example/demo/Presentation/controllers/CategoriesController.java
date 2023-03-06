package com.example.demo.Presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Buisness.Services.interfaces.CategoriesService;
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
}
