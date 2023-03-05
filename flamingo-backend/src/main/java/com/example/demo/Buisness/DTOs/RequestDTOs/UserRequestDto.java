package com.example.demo.Buisness.DTOs.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    
    private String username;
    private String password;
    private String FirstName;
    private String LastName;
    private String gender;
    private String email;
    private String phone;


}
