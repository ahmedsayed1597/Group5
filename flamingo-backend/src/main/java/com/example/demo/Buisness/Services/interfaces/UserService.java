package com.example.demo.Buisness.Services.interfaces;

import com.example.demo.Repository.Dao.interfaces.UserDao;
import com.example.demo.Repository.Entities.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    
    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao=userDao;
    }

     public void addUser(User user){

         userDao.save(user);
     }

     public List<User> getAll(){

        return userDao.findAll();
     }
}
