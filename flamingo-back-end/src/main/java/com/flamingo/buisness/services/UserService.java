package com.flamingo.buisness.services;

import com.flamingo.presentation.dto.AddressDTO;
import com.flamingo.presentation.dto.UserDTO;
import com.flamingo.presentation.dto.UserRequestDTO;
import com.flamingo.presentation.dto.UserResponseDTO;

public interface UserService {
	UserRequestDTO registerUser(UserRequestDTO userRequestDTO);

	UserResponseDTO getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	UserDTO getUserById(Long userId);

	UserRequestDTO updateUser(Long userId, UserRequestDTO userRequestDTO);

	String deleteUser(Long userId);

	UserRequestDTO registerAddress(Long userId,AddressDTO addressDTO);

	public UserRequestDTO getAddress(Long userId);
	public UserRequestDTO updateAddress(Long userId,AddressDTO addressDTO) ;
	public String deleteAddress(Long userId) ;
}