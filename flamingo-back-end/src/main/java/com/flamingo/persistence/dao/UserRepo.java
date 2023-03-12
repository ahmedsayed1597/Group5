package com.flamingo.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flamingo.persistence.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{
    @Query("SELECT u FROM User u JOIN FETCH u.addresses a WHERE a.addressId = ?1")
	List<User> findByAddress(Long addressId);
	
	Optional<User> findByEmail(String email);
}
