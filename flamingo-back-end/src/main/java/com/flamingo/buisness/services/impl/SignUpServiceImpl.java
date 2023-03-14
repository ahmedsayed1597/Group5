package com.flamingo.buisness.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flamingo.buisness.services.interfaces.SignUpService;
import com.flamingo.config.JwtService;
import com.flamingo.persistence.dao.RoleRepo;
import com.flamingo.persistence.dao.UserRepo;
import com.flamingo.persistence.entities.Cart;
import com.flamingo.persistence.entities.Role;
import com.flamingo.persistence.entities.User;
import com.flamingo.presentation.dto.UserRequestDTO;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {
    
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final ModelMapper modelMapper;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final JwtService jwtService;
    
    public String saveUser(UserRequestDTO signUpDTO) {

                 User user = modelMapper.map(signUpDTO, User.class);



                    user.setPassword(encoder.encode(signUpDTO.getPassword()));
                    
                    User registeredUser = userRepo.save(user);
                    Cart cart = new Cart();
                    user.setCart(cart);
                       Role role = roleRepo.findById(102L).get();
                       user.getRoles().add(role);
                    cart.setUser(registeredUser);

        
                    String jwtToken = jwtService.generateToken(user);

        
                return jwtToken;
            }
}
