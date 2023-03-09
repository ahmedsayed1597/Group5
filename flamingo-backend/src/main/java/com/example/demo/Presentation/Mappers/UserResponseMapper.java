package com.example.demo.presentation.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.example.demo.buisness.dtos.responseDTOs.UserResponseDto;
import com.example.demo.repository.entities.User;

@Mapper(componentModel = "spring")
public abstract class UserResponseMapper {
    
    @Mappings({
        @Mapping(source = "fullName",target = "firstName" , qualifiedByName = "getFirstName"),
        @Mapping(source = "fullName",target = "lastName" , qualifiedByName = "getLastName")

    })
    public abstract User fromDtoToEntity(UserResponseDto user);

    @Mapping( target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    public abstract UserResponseDto fromModelToDto(User user);

    public abstract List<UserResponseDto> fromModelToDto(List<User> user);


    @Named("getFirstName")
    public String getFirstName(String fullName){
        return fullName.split(" ")[0];
    }
    @Named("getLastName")
    public String getLastName(String fullName){
        return fullName.split(" ")[1];
    }
}
