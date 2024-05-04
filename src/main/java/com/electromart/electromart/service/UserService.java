package com.electromart.electromart.service;

import com.electromart.electromart.entity.User;
import com.electromart.electromart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    //Query for returning all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public String encodePassword (String passwordFromInput) {
        passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(passwordFromInput);
    }

    public long createNewUser () {
        String email = "email from user input";
        String password = "password from user input";
        String firstName = "first name from input";
        String lastName = "last name from input";

        String encodedPassword = encodePassword(password);
        User user = new User(encodedPassword,firstName,lastName,email);
        userRepository.save(user);
        return user.getUserId();
    }
}

