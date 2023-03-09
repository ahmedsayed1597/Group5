package com.example.demo.presentation.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.buisness.services.interfaces.ProductService;
import com.example.demo.presentation.mappers.ProductMapperAbstract;
import com.example.demo.presentation.mappers.ProductResponseMapper;
import com.example.demo.buisness.dtos.requestDTOs.ProductRequestDto;
import com.example.demo.buisness.dtos.responseDTOs.ProductResponseDto;
import com.example.demo.presentation.responseViewModel.ProductResponse;
import com.example.demo.repository.entities.Product;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;
    private final ProductResponseMapper mapper;
    private final ProductMapperAbstract productMapperAbstract;



    @GetMapping("/products")
    public ResponseEntity<ProductResponse> getAllProduct() {

        List<Product> products = productService.getAllProduct();
        List<ProductResponseDto>prductsDto=mapper.fromEntityToDto(products);
        ProductResponse response =new ProductResponse();
        response.setMessage("accepted");
        response.setData(prductsDto);
        return new ResponseEntity<ProductResponse> ( response,HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getCategorybyid(@PathVariable int id){
        ProductResponse response =new ProductResponse();
        response.setMessage("accepted");
        response.setData(new ArrayList<>(Arrays.asList(mapper.fromEntityToDto(productService.getByID(id)))));
        return new ResponseEntity<ProductResponse> ( response,HttpStatus.OK);
}

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequestDto product){
        return ResponseEntity.ok(productService.insertproduct(productMapperAbstract.fromDtoToEntity(product)));
    }
}