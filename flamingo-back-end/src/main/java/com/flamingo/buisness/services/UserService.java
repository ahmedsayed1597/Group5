package com.flamingo.buisness.services;

import com.flamingo.presentation.dto.AddressDTO;
import com.flamingo.presentation.dto.UserDTO;
import com.flamingo.presentation.dto.UserRequestDTO;
import com.flamingo.presentation.dto.UserResponse;

public interface UserService {
	
	UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	UserDTO getUserById(Long userId);
	
	UserRequestDTO updateUser(Long userId, UserRequestDTO userRequestDTO);
	
	String deleteUser(Long userId);

	UserDTO registerAddress(Long userId,AddressDTO addressDTO);

	public UserDTO getAddress(Long userId);
	public UserDTO updateAddress(Long userId,AddressDTO addressDTO) ;
	public String deleteAddress(Long userId) ;
}
