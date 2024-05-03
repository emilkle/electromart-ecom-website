package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressID;

    @ManyToOne
    @JoinColumn(name="user_ID")
    private User userID;
    private String address;
    private String postalCode;
    private String city;
    private String country;

    public Address () {}

    public Address (User userid, String address, String postalCode, String city, String country) {
        this.userID = userid;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;

    }

    public long getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
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
