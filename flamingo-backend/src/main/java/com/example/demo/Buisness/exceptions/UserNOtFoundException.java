package com.example.demo.buisness.exceptions;

public class UserNOtFoundException extends RuntimeException {
    
    public UserNOtFoundException(String error){
        super(error);
    }
}
