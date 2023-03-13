package com.flamingo.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private Long userId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String password;
}
