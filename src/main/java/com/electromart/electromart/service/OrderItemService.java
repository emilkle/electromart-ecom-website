package com.electromart.electromart.service;

import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.entity.OrderItem;
import com.electromart.electromart.repository.BrandRepository;
import com.electromart.electromart.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
}
