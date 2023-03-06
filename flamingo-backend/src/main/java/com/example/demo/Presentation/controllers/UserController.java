package com.example.demo.Presentation.controllers;

import com.example.demo.Buisness.DTOs.RequestDTOs.UserRequestDto;
import com.example.demo.Buisness.Services.interfaces.UserService;
import com.example.demo.Presentation.Mappers.impl.UserMapperImpl;
import com.example.demo.Presentation.ResponseViewModel.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String test(){
        return "hello";
    }

    @PostMapping("/Register")
    public UserResponse addUser(@RequestBody UserRequestDto user){
        return userService.addUser(UserMapperImpl.fromDtoToEntity(user));

    }

    @PostMapping("/Login")
    public UserResponse login(@RequestBody UserRequestDto user){   
        return userService.getEmailAndPassword(UserMapperImpl.fromDtoToEntity(user));
                   
    }

    
}
