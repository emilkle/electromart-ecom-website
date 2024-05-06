package com.electromart.electromart.service;

import com.electromart.electromart.dto.AddressDTO;
import com.electromart.electromart.dto.UserDTO;
import com.electromart.electromart.entity.Address;
import com.electromart.electromart.entity.User;
import com.electromart.electromart.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public String encodePassword (String passwordFromInput) {
        passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(passwordFromInput);
    }


    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> convertToDTO(user))
                .collect(Collectors.toList());
    }

    public UserDTO addUser (UserDTO userDTO) {
        String encodedPassword = encodePassword(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        User user = convertToEntity(userDTO);
        User savedUSer = userRepository.save(user);
        return convertToDTO(savedUSer);
    }

    public Optional<UserDTO> getUserByID (long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> convertToDTO(user));
    }

    private UserDTO convertToDTO (User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setUserID(user.getUserId());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        // Create new address object to store the converted addressDTO object
        User user = new User();
        // Using BeanUtils library for copying the values in the addressDTO to the address.
        BeanUtils.copyProperties(userDTO, user);
        System.out.println("user DTO id: " + userDTO.getUserID() +" User id: " + user.getUserId());
        user.setUserId(userDTO.getUserID());
        return user;
    }
}

