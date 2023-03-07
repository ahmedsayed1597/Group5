package com.example.demo.repository.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entities.User;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Integer> {

//public boolean isEmailExsist(User user);

    public User getUserByEmail(String Email);

}
