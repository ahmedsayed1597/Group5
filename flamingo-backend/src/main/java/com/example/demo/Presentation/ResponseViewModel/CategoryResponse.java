package com.example.demo.presentation.responseViewModel;

public class CategoryResponse {

    private String message;

    public CategoryResponse(){

    }
    
    public CategoryResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    
}
