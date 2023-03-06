package com.example.demo.Presentation.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Buisness.Services.interfaces.CategoryService;

@RestController
// @RequestMapping("/category")

public class CategoryController {

	@Autowired
	private CategoryService categoryService;


	@GetMapping(value ="/category")
    public String test(){
        return "hello";
    }

}