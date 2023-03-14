package com.flamingo.buisness.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.flamingo.persistence.entities.Role;
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
    public String userValidation(LoginDTO credintials) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credintials.getEmail(),
                        credintials.getPassword()
                )
        );

      User user = re.findByEmail(credintials.getEmail()).orElseThrow(()->new notFoundException("no such user"));

      Map<String,Object> claims = new  HashMap<>();
      List<Role> roles = user.getRoles();
      
      System.out.println(roles.get(0).getRoleName());
      claims.put("Role_",roles.get(0).getRoleName());

      String jwtToken = jwtService.generateToken(claims,user);

      return jwtToken;

        
   }


    
}
