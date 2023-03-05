package com.example.demo.Presentation.Mappers;

import org.mapstruct.factory.Mappers;

import com.example.demo.Buisness.DTOs.RequestDTOs.UserRequestDto;
import com.example.demo.Repository.Entities.User;

public interface UserMapper {
    UserMapper instance =Mappers.getMapper(UserMapper.class);
    User fromUserDtoToUser(UserRequestDto user);

    UserRequestDto fromModelToDto(User user);
}
