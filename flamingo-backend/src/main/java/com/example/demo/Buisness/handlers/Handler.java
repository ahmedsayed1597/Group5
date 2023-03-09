package com.example.demo.buisness.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.buisness.errors.ErrorMessage;
import com.example.demo.buisness.exceptions.ProductNotFoundException;

@ControllerAdvice
public class Handler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(ProductNotFoundException ex){

        return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(),ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(Exception ex){

        return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(),ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
}
