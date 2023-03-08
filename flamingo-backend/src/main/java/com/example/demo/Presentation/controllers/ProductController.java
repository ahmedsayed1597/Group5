package com.example.demo.presentation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.buisness.dtos.requestDTOs.ProductRequestDto;
import com.example.demo.buisness.services.interfaces.ProductService;
import com.example.demo.presentation.mappers.ProductMapperAbstract;
import com.example.demo.presentation.responseViewModel.ProductResponse;
import com.example.demo.repository.entities.Product;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapperAbstract productMapperAbstract;

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getCategorybyid(@PathVariable int id) {
        return productService.getByID(id);
    }

    @PostMapping("/products")
    public ProductResponse addProduct(@RequestBody ProductRequestDto product){
       return productService.insertproduct(productMapperAbstract.fromDtoToEntity(product));
    }
}