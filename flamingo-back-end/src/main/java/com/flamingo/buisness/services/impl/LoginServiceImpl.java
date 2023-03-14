package com.flamingo.buisness.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flamingo.buisness.exception.notFoundException;
import com.flamingo.buisness.services.UserService;
import com.flamingo.buisness.services.interfaces.LoginService;
import com.flamingo.config.JwtService;
import com.flamingo.persistence.dao.UserRepo;
import com.flamingo.persistence.entities.User;
import com.flamingo.presentation.dto.LoginDTO;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService{


    private final  UserRepo re;

    // private ModelMapper loingResponceMapper ;

    private final JwtService jwtService;
    
    private final AuthenticationManager authenticationManager;
    
    // private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public String userValidation(LoginDTO ldto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        ldto.getEmail(),
                        ldto.getPassword()
                )
        );

      User u = re.findByEmail(ldto.getEmail()).orElseThrow(()->new notFoundException("no such user"));

      String jwtToken = jwtService.generateToken(u);

      return jwtToken;

        
   }


    
}
