package com.example.demo.Presentation.controllers;

import com.example.demo.Buisness.DTOs.RequestDTOs.UserRequestDto;
import com.example.demo.Buisness.Services.interfaces.UserService;
import com.example.demo.Presentation.Mappers.UserMapper;
import com.example.demo.Repository.Entities.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String test(){
        return "hello";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll(){

        return ResponseEntity.ok().body(userService.getAll());
    }

    @PostMapping("/users")
    public UserRequestDto addUser(@RequestBody UserRequestDto user){

        userService.addUser(UserMapper.instance.fromDtoToEntity(user));
        return user;
    }

}
