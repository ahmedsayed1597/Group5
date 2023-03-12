package com.flamingo.presentation.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flamingo.JWT.JWTUtil;
import com.flamingo.buisness.services.UserService;
import com.flamingo.exception.UserNotFoundException;
import com.flamingo.presentation.dto.LoginDTO;
import com.flamingo.presentation.dto.UserDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerHandler(@Valid @RequestBody UserDTO user)
            throws UserNotFoundException {
        String encodedPass = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPass);

        UserDTO userDTO = userService.registerUser(user);

        String token = jwtUtil.generateToken(userDTO.getEmail());

        Map<String, Object> hash_map = new HashMap<>();
        hash_map.put("jwt-token", token);

        return new ResponseEntity<Map<String, Object>>(hash_map,HttpStatus.CREATED );
        
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@Valid @RequestBody LoginDTO credentials) {

        UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
                credentials.getEmail(), credentials.getPassword());

        authenticationManager.authenticate(authCredentials);

        String token = jwtUtil.generateToken(credentials.getEmail());

        Map<String, Object> hash_map = new HashMap<>();
        hash_map.put("jwt-token", token);

        return hash_map;
    }
}