package com.flamingo.buisness.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flamingo.persistence.dao.UserRepo;
import com.flamingo.persistence.entities.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username).get();
		if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getAuthorities()
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
		
		// return user.map(UserInfoConfig::new).orElseThrow(() -> new ResourceNotFoundException("User", "email", username));
	}
	// private Set<SimpleGrantedAuthority> getAuthority(User user) {
    //     Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    //     user.getRoles().forEach(role -> {
    //         authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
    //     });
    //     return authorities;
    }

	
    
