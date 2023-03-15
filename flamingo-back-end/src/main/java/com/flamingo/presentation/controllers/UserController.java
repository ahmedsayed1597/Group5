package com.flamingo.presentation.controllers;

import com.flamingo.presentation.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flamingo.buisness.services.UserService;
import com.flamingo.config.AppConstants;
import com.flamingo.presentation.dto.UserDTO;
import com.flamingo.presentation.dto.UserResponseDTO;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/admin/users")
	public ResponseEntity<UserResponseDTO> getUsers(
			@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_USERS_BY, required = false) String sortBy,
			@RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {

		UserResponseDTO userResponse = userService.getAllUsers(pageNumber, pageSize, sortBy, sortOrder);

		return new ResponseEntity<UserResponseDTO>(userResponse, HttpStatus.FOUND);
	}

	@GetMapping("/public/users/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
		UserDTO user = userService.getUserById(userId);

		return new ResponseEntity<UserDTO>(user, HttpStatus.FOUND);
	}

	 @PutMapping("/public/users/{userId}")
	 public ResponseEntity<UserRequestDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO, @PathVariable Long userId) {
	 	UserRequestDTO updatedUser = userService.updateUser(userId, userRequestDTO);

	 	return new ResponseEntity<UserRequestDTO>(updatedUser, HttpStatus.OK);
	 }

	@DeleteMapping("/admin/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		String status = userService.deleteUser(userId);

		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
}
