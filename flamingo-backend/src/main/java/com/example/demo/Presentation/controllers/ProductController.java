package com.example.demo.Presentation.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Buisness.Services.interfaces.ProductService;
import com.example.demo.Repository.Entities.Product;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String getAllProduct(){
        return productService.getAllProduct();
    }


    @GetMapping("/{id}")
    public Optional <Product> getCategorybyid(@PathVariable int id){
        return productService.getByID(id);
}

}