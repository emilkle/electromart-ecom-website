package com.electromart.electromart.entity;

import jakarta.persistence.*;

/**
 * The User class represents a User entity of the ecommerce website.
 */

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_Id;

    //@Column(nullable = false, length = 64)
    private String password;

    //@Column(nullable = false, length = 100, unique = true)
    private String email;

    //@Column(nullable = false, length = 100)
    private String name;

    //@Column(length = 255)
    private String address;

    /**
     * Constructor for the user entity.
     * This constructor has no arguments, as it is required by JPA.
     */
    public User() {

    }

    /**
     * Constructor no 2 with arguments for the User entity.
     * @param password the users password
     * @param email the users email address
     * @param name the users name
     * @param address the users address
     */
    public User(String password, String name, String email, String address) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * getUserID
     * @return userID
     */
    public Long getUserId() {
        return user_Id;
    }

    /**
     * setUserID
     * @param userId the specified userID
     */
    public void setUserId(Long userId) {
        this.user_Id = userId;
    }

    /**
     * getPassword
     * @return the users password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword
     * @param password the specified password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getName
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * @param name the specified name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getEmail
     * @return the email address for the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail
     * @param email the specified email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getAddress
     * @return the address of the user
     */
    public String getAddress() {
        return address;
    }

    /**
     * setAddress
     * @param address the specified address of the user
     */
    public void setAddress(String address) {
        this.address = address;
    }

}

