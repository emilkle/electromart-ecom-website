package com.electromart.electromart.service;

import com.electromart.electromart.entity.Address;
import com.electromart.electromart.entity.User;
import com.electromart.electromart.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    /**
     * Creates a new address to a corresponding user.
     * @param userID is returned from createUser method and used to create the corresponding address
     */
    public Address createAddress (User userID) {
        String address = "address from input";
        String postalCode = "postal code from input";
        String city = "city from input";
        String country = "country from input";

        Address userAddress = new Address(userID, address, postalCode, city, country);
        return userAddress;
    }
}
