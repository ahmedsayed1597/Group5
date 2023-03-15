package com.flamingo.buisness.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flamingo.persistence.entities.Cart;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.flamingo.buisness.exception.notFoundException;
import com.flamingo.config.JwtService;
import com.flamingo.persistence.dao.UserRepo;
import com.flamingo.persistence.entities.Role;
import com.flamingo.persistence.entities.User;
import com.flamingo.presentation.dto.LoginDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {

        private final JwtService jwtService;
    
        private final UserRepo userRepo;
    
        private final AuthenticationManager authenticationManager;
    
        public String createJwtToken(LoginDTO credentials) throws Exception {
            String userName = credentials.getEmail();
            String userPassword = credentials.getPassword();
            authenticate(userName, userPassword);
    
            UserDetails userDetails = loadUserByUsername(userName);
    
            User user = userRepo.findByEmail(userName).orElseThrow(()->new notFoundException("user not found"));
            Cart cart=user.getCart();
            Map<String,String> claims = new HashMap<>();
            if (user.getRoles().get(0) != null) {
                claims.put("Role_", user.getRoles().get(0).getRoleName());
            }
            claims.put("CartId", cart.getCartId().toString());
            claims.put("UserId", user.getUserId().toString());

            String newGeneratedToken = jwtService.generateToken(claims,userDetails);

            return  newGeneratedToken;
        }
    
        @Override
        public UserDetails loadUserByUsername(String username) throws notFoundException {
            User user = userRepo.findByEmail(username).get();
    
            if (user != null) {
                return new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        getAuthority(user)
                );
            } else {
                throw new notFoundException("User not found with username: " + username);
            }
        }
    
        private List getAuthority(User user) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("Role_" + role.getRoleName()));
            });
            return authorities;
        }
    
        private void authenticate(String userName, String userPassword) throws Exception {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
            } catch (DisabledException e) {
                e.getMessage();
            } catch (BadCredentialsException e) {
                e.getMessage();
            }
        }

    }
    