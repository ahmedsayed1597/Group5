package com.flamingo.presentation.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flamingo.buisness.services.interfaces.SignUpService;
import com.flamingo.exception.UserNotFoundException;
import com.flamingo.presentation.dto.LoginDTO;
import com.flamingo.presentation.dto.UserRequestDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = {"*"},methods = {RequestMethod.POST})


@RequiredArgsConstructor
public class AuthController {

    private final SignUpService signUpService;
    private final com.flamingo.buisness.services.impl.LoginService loginService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerHandler(@Valid @RequestBody UserRequestDTO user)
            throws UserNotFoundException {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        System.out.println(encodedPass);
        user.setPassword(encodedPass);
        Map<String, Object> hash_map = new HashMap<>();
        hash_map.put("jwtToken", signUpService.saveUser(user));
        return new ResponseEntity<Map<String, Object>>(hash_map, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@Valid @RequestBody LoginDTO credentials) throws Exception {
        Map<String, Object> hash_map = new HashMap<>();
        hash_map.put("jwtToken", loginService.createJwtToken(credentials));
        return hash_map;
    }
}