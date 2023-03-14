package com.flamingo.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flamingo.buisness.services.UserService;
import com.flamingo.presentation.dto.UserDTO;
import com.flamingo.presentation.dto.UserResponse;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"},methods = {RequestMethod.POST})

public class UserController {
    public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "2";
    public static final String SORT_USERS_BY = "userId";
	public static final String SORT_ORDERS_BY = "totalAmount";
    public static final String SORT_DIR = "asc";

	
	@Autowired
	private UserService userService;
	
	@GetMapping("/admin/users")
	public ResponseEntity<UserResponse> getUsers(
			@RequestParam(name = "pageNumber", defaultValue = PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = SORT_USERS_BY, required = false) String sortBy,
			@RequestParam(name = "sortOrder", defaultValue = SORT_DIR, required = false) String sortOrder) {
		
		UserResponse userResponse = userService.getAllUsers(pageNumber, pageSize, sortBy, sortOrder);
		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.FOUND);
	}
	
	@GetMapping("/public/users/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
		UserDTO user = userService.getUserById(userId);
		
		return new ResponseEntity<UserDTO>(user, HttpStatus.FOUND);
	}
	

	
	@DeleteMapping("/admin/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		String status = userService.deleteUser(userId);
		
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
}
