package com.flamingo.buisness.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flamingo.buisness.error.ErrorMessage;
import com.flamingo.buisness.exception.AlreadyExist;
import com.flamingo.buisness.exception.notFoundException;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(notFoundException.class)
    public ResponseEntity<ErrorMessage> handler(notFoundException ex){

        return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExist.class)

    public ResponseEntity<ErrorMessage> handler(AlreadyExist ex){

        return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage()),HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrorMessage> handler(Exception ex){
        ex.printStackTrace();
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage()),HttpStatus.NOT_FOUND);
    }
}
