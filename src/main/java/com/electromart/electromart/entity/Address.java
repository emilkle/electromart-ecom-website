package com.electromart.electromart.entity;

import jakarta.persistence.*;

/**
 * The Address class represents all the address entities that are stored in the database.
 */
@Entity
@Table(name="Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_ID")
    private Long addressID;

    @ManyToOne
    @JoinColumn(name="user_ID")
    private User userID;

    private String address;
    private String postalCode;
    private String city;
    private String country;

    public Address () {}

    public Address (User userId, String address, String postalCode, String city, String country) {
        this.userID = userId;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;

    }

    public long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public User getUserID() {
        return userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
