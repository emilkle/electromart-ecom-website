package com.electromart.electromart.dto;

import com.electromart.electromart.entity.User;

public class AddressDTO {

    private Long addressID;
    private User userID;
    private String address;
    private String postalCode;
    private String city;
    private String country;

    public AddressDTO() {}

    public AddressDTO(Long addressID, User userID, String address, String postalCode, String city, String country) {
        this.addressID = addressID;
        this.userID = userID;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
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
