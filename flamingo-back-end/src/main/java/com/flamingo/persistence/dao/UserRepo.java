package com.flamingo.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flamingo.persistence.entities.User;
public interface UserRepo extends JpaRepository<User, Long> {

	
	Optional<User> findByEmail(String email);
}