package com.example.demo.Presentation.Mappers.impl;

import com.example.demo.Buisness.DTOs.RequestDTOs.UserRequestDto;
import com.example.demo.Repository.Entities.User;

public class UserMapperImpl {

    public static UserRequestDto fromEntityToDto(User user){
        return new UserRequestDto(user.getUsername(), user.getPassword(), user.getFirstName(),
                                    user.getLastName(),user.getGender(),user.getEmail(),user.getPhone());
    }

    public static User fromDtoToEntity(UserRequestDto user){

        return new User(user.getUsername(), user.getPassword(), user.getFirstName(),
                user.getLastName(),user.getGender(),user.getEmail(),user.getPhone());
    }
}
