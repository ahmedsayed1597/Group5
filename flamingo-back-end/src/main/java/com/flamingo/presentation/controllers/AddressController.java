package com.flamingo.presentation.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flamingo.buisness.services.UserService;
import com.flamingo.presentation.dto.AddressDTO;
import com.flamingo.presentation.dto.UserDTO;
import com.flamingo.presentation.dto.UserRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/public/users/{userId}")
@CrossOrigin(origins = {"*"},methods = {RequestMethod.POST})
@RequiredArgsConstructor
public class AddressController {
    
    private final UserService userService;


    @PostMapping("/address")
    public UserRequestDTO addAdress(@PathVariable Long userId,@RequestBody AddressDTO addressDTO){

        return userService.registerAddress(userId, addressDTO);
    } 

    @PutMapping("/address")
    public UserRequestDTO updateAddress(@PathVariable Long userId,@RequestBody AddressDTO addressDTO){

        return userService.updateAddress(userId, addressDTO);
    } 

    @GetMapping("/address")
    public UserRequestDTO getAdress(@PathVariable Long userId){

        return userService.getAddress(userId);
    } 

    @DeleteMapping("/address")
    public String deleteAdress(@PathVariable Long userId){

        return userService.deleteAddress(userId);
    } 
}
