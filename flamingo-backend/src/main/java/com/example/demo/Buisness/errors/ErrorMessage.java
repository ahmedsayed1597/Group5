package com.example.demo.buisness.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private int staus;
    private String message;

    
}
