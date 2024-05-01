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
}
