package com.flamingo.presentation.controllers;

import com.flamingo.buisness.services.interfaces.ProductService;
import com.flamingo.persistence.entities.Product;
import com.flamingo.presentation.dto.ProductDto;
import com.flamingo.presentation.responseviewmodel.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;



    @PostMapping(value = "/admin/categories/{categoryId}/product",
                            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE ,"application/json"})
    public ResponseEntity<ProductDto> addProductWithImageJSon(@PathVariable Long categoryId, @RequestPart("product") Product product ,
                                                              @RequestPart("image")MultipartFile image ) throws IOException {

        return new ResponseEntity<>(productService.addProduct(categoryId, product,image), HttpStatus.CREATED);
    }


    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "field", defaultValue = "productId", required = false) String field,
            @RequestParam(name = "orderBy", defaultValue = "asc", required = false) String orderBy) {


        return new ResponseEntity<>(productService.getAllProducts(pageNumber, pageSize, field, orderBy), HttpStatus.OK);

    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "productId", required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "asc", required = false) String sortOrder) {


        return new ResponseEntity<>(productService.searchByCategory(categoryId,pageNumber,pageSize,sortBy,sortOrder), HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(
            @PathVariable String keyword,
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "productId", required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "asc", required = false) String sortOrder) {


        return new ResponseEntity<ProductResponse>( productService.searchProductByKeyword(keyword, pageNumber, pageSize, sortBy,
                sortOrder), HttpStatus.FOUND);
    }

    @GetMapping("/public/products/{productId}/image")
    public ResponseEntity<?> getImages(@PathVariable Long productId) throws IOException {
        return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE)).body(productService.downloadImages(productId));
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody Product product,
                                                    @PathVariable Long productId) {

        return new ResponseEntity<ProductDto>(productService.updateProduct(productId, product), HttpStatus.OK);
    }

    @PutMapping(value = "/admin/products/{productId}/image",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductDto> updateProductImage(@PathVariable Long productId, @RequestPart MultipartFile image) throws IOException {

        return new ResponseEntity<ProductDto>(productService.updateProductImage(productId, image), HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<String> deleteProductByCategory(@PathVariable Long productId) {

        return new ResponseEntity<String>(productService.deleteProduct(productId), HttpStatus.NO_CONTENT);
    }

}
