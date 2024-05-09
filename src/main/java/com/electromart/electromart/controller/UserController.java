package com.electromart.electromart.controller;

import com.electromart.electromart.dto.UserDTO;
import com.electromart.electromart.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService) { this.userService = userService; }

    @GetMapping({"","/"})
    public List<UserDTO> fetchAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found");
        } else {
            return users;
        }
    }

    @GetMapping("/user_id={id}")
    public ResponseEntity<?> getUserByID(@PathVariable long id) {
        Optional<UserDTO> optionalUSer = userService.getUserByID(id);
        if (optionalUSer.isPresent()) {
            return new ResponseEntity<>(optionalUSer.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with ID: '" + id + "' not found");
        }
    }

    @PostMapping({"","/"})
    public ResponseEntity<UserDTO> addUser (@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.addUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/user_id={id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().body("User with ID: '" + id + "' successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        }
    }
}
