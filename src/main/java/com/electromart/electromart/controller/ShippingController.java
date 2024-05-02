package com.electromart.electromart.controller;

import com.electromart.electromart.entity.Shipping;
import com.electromart.electromart.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @GetMapping("")
    public List<Shipping> fetchAllShippings() {
        return shippingService.getAllShippings();
    }
}
