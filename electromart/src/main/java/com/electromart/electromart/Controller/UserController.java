package com.electromart.electromart.Controller;

import com.electromart.electromart.entities.User;
import com.electromart.electromart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //means this is a controller
@RequestMapping("/users") //means the url starts with /users after application path (i.e. the endpoint)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> fetchAllUsers() {
        return userService.getAllUsers();
    }
}
