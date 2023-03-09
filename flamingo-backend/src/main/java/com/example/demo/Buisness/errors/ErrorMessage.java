package com.example.demo.buisness.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private int staus;
    private String message;

    
}
