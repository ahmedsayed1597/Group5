package com.flamingo.presentation.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	
	
	@Email
	@Column(unique = true, nullable = false)
	private String email;

	private String password;
}
