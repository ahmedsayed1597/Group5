package com.example.demo.repository.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entities.User;

public interface UserDao extends JpaRepository<User,Integer> {

//public boolean isEmailExsist(User user);
}
