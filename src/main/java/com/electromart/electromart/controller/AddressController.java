package com.electromart.electromart.controller;

import com.electromart.electromart.entity.Address;
import com.electromart.electromart.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public List<Address> fetchAllAddress() {
        return addressService.getAllAddresses();
    }
}
