package com.example.demo.Buisness.Services.interfaces;

import com.example.demo.Presentation.ResponseViewModel.UserResponse;
import com.example.demo.Repository.Dao.interfaces.UserDao;
import com.example.demo.Repository.Entities.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    
    @Autowired
    private UserDao userDao;

    public UserResponse addUser(User user) {
        List<User> users = new ArrayList<>();
        users = userDao.findAll();
        UserResponse response = new UserResponse();
        User userLoop = new User();

        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            userLoop = iterator.next();
            if ((userLoop.getEmail()).equals(user.getEmail())){
                response.setMessage("This email already exist");
                return response;
            } if(userLoop.getUsername().equals(user.getUsername())){
                response.setMessage("This username is used");
                return response;
            }
        }
        userDao.save(user);
        response.setMessage("User Created");
        return response;
    }

    public UserResponse getEmailAndPassword(User user) {
        // return userDao.isEmailExsist(user);
        UserResponse response = new UserResponse();

        List<User> users = new ArrayList<>();
        users = userDao.findAll();
        Iterator<User> iterator = users.iterator();
        User userLoop = new User();
        while (iterator.hasNext()) {
            userLoop = iterator.next();
            if ((userLoop.getEmail()).equals(user.getEmail())) {
                if (((userLoop.getPassword()).equals(user.getPassword()))) {

                    response.setMessage("Succesfull Login");
                    return response;
                }
                else {
                    response.setMessage("Incorrect Password");
                    return response;
                }
               

            }
        }
        response.setMessage("User Doesn't Exist");
        return response;
    }

}
