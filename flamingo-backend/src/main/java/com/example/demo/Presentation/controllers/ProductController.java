package com.example.demo.presentation.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.buisness.exceptions.ProductNotFoundException;
import com.example.demo.buisness.services.interfaces.ProductService;
import com.example.demo.presentation.mappers.ProductResponseMapper;
import com.example.demo.presentation.responseViewModel.ProductResponse;
import com.example.demo.repository.entities.Product;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductResponseMapper mapper;
    @GetMapping("/products")
    public String getAllProduct(){
        return productService.getAllProduct();
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getCategorybyid(@PathVariable int id){
        ProductResponse response =new ProductResponse();
        response.setMessage("accepted");
        response.setData(new ArrayList<>(Arrays.asList(mapper.fromEntityToDto(productService.getByID(id)))));
        return new ResponseEntity<ProductResponse> ( response,HttpStatus.FOUND);
}

}