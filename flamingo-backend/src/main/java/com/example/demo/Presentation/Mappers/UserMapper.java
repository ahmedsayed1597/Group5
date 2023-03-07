package com.example.demo.presentation.mappers;

import org.mapstruct.Mapper;


import com.example.demo.buisness.dtos.requestDTOs.UserRequestDto;
import com.example.demo.repository.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    User fromDtoToEntity(UserRequestDto user);

    UserRequestDto fromModelToDto(User user);
}
