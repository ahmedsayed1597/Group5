package com.example.demo.buisness.services.interfaces;

import com.example.demo.repository.entities.User;

public class UserUtils {
    
    public static User userMapper(User updatedUser ,User user){

        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setGender(user.getGender());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhone(user.getPhone());
        return updatedUser;
    }
}
