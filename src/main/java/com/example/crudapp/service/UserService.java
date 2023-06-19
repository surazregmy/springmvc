package com.example.crudapp.service;

import com.example.crudapp.entity.User;
import com.example.crudapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String validateEmail(User user){
        String message = "";
        User exisitingUser = userRepository.getUserByEmail(user.getEmail());
        if(exisitingUser!=null){
            message =  "User with the email already Exists";
        }
        System.out.println("The user message is "+ message);
        return message;
    }

    public User signUp(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }



}
