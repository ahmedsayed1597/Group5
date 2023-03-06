package com.example.demo.Presentation.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.Buisness.DTOs.RequestDTOs.UserRequestDto;
import com.example.demo.Repository.Entities.User;

@Mapper
public interface UserMapper {
    UserMapper instance =Mappers.getMapper(UserMapper.class);


    UserRequestDto fromModelToDto(User user);

    User fromDtoToEntity(UserRequestDto user);
}
