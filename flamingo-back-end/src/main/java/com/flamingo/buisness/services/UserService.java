package com.flamingo.buisness.services;

import com.flamingo.presentation.dto.UserDTO;

public interface UserService {
	UserDTO registerUser(UserDTO userDTO);
	
	// UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	// UserDTO getUserById(Long userId);
	
	// UserDTO updateUser(Long userId, UserDTO userDTO);
	
	// String deleteUser(Long userId);
}
