package com.example.demo.Presentation.ResponseViewModel;

public class UserResponse {

    private String message;
    private String token;

    
    public UserResponse() {
        this.message = "";
        this.token = "";
    }

    public UserResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getMessage() {
        return message;
    }
    public String getToken() {
        return token;
    }
    

    
}
