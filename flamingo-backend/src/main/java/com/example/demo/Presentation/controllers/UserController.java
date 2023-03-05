package com.example.demo.Presentation.controllers;

import com.example.demo.Buisness.DTOs.RequestDTOs.UserRequestDto;
import com.example.demo.Buisness.Services.interfaces.UserService;
import com.example.demo.Presentation.Mappers.impl.UserMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*") 

public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String test(){
        return "hello";
    }

    @PostMapping("/users")
    public UserRequestDto addUser(@RequestBody UserRequestDto user){

        userService.addUser(UserMapperImpl.fromDtoToEntity(user));
        return user;
    }

}
