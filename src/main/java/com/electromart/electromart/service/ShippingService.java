package com.electromart.electromart.service;

import com.electromart.electromart.entity.Shipping;
import com.electromart.electromart.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    public List<Shipping> getAllShippings() {
        return shippingRepository.findAll();
    }
}
