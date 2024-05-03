package com.electromart.electromart.service;

import com.electromart.electromart.entity.User;
import com.electromart.electromart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Query for returning all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public long createNewUser () {
        String email = "email from user input";
        String password = "password from user input";
        String firstName = "first name from input";
        String lastName = "last name from input";

        User user = new User(password,firstName,lastName,email);

        return user.getUserId();
    }
}

