package com.electromart.electromart.dto;

import com.electromart.electromart.repository.UserRepository;

public class UserDTO {

    private Long userID;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public UserDTO() {}

    public UserDTO(Long userID, String password, String email, String firstName, String lastName) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
