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
    @Column(name="user_ID")
    private long user_Id;

    //@Column(nullable = false, length = 64)
    private String password;

    //@Column(nullable = false, length = 100, unique = true)
    private String email;

    //@Column(nullable = false, length = 100)
    private String firstName;

    private String lastName;


    /**
     * Constructor for the user entity.
     * This constructor has no arguments, as it is required by JPA.
     */
    public User() {

    }

    /**
     * Constructor no 2 with arguments for the User entity.
     * @param firstName the users first name
     * @param lastName last name of user
     * @param password the users password
     * @param email the users email address
     */
    public User(String password, String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * getUserID
     * @return userID
     */
    public long getUserId() {
        return user_Id;
    }

    /**
     * setUserID
     * @param userId the specified userID
     */
    public void setUserId(long userId) {
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
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setName
     * @param firstName the specified first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

}

