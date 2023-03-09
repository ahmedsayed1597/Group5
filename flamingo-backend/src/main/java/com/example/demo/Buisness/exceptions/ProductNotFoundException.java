package com.example.demo.buisness.exceptions;

public class ProductNotFoundException extends RuntimeException {
    

    public ProductNotFoundException(String message){
        super(message);
    }
}
