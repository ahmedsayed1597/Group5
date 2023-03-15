package com.flamingo.presentation.dto;


import java.util.HashSet;
import java.util.Set;

import com.flamingo.persistence.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String password;
	private String street;
	private String city;
	private String country;
	private Set<Role> roles = new HashSet<>();
	private CartDTO cart;
}
