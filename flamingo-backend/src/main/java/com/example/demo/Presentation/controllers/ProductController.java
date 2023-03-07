package com.example.demo.presentation.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.buisness.services.interfaces.ProductService;
import com.example.demo.repository.entities.Product;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public String getAllProduct(){
        return productService.getAllProduct();
    }


    @GetMapping("/{id}")
    public Optional <Product> getCategorybyid(@PathVariable int id){
        return productService.getByID(id);
}

}