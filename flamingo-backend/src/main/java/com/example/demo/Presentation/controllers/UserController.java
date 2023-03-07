package com.example.demo.presentation.controllers;

import com.example.demo.buisness.dtos.requestDTOs.UserRequestDto;
import com.example.demo.buisness.services.interfaces.UserService;
import com.example.demo.presentation.responseViewModel.UserResponse;

import lombok.RequiredArgsConstructor;

import com.example.demo.presentation.mappers.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/")
    public String test(){
        return "hello";
    }

    @PostMapping("/Register")
    public UserResponse addUser(@RequestBody UserRequestDto user){
        return userService.addUser(userMapper.fromDtoToEntity(user));

    }

    @PostMapping("/Login")
    public UserResponse login(@RequestBody UserRequestDto user){   
        return userService.getEmailAndPassword(userMapper.fromDtoToEntity(user));
                   
    }

    
}
