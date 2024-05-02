package com.electromart.electromart.controller;

import com.electromart.electromart.entity.Order;
import com.electromart.electromart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public List<Order> fetchAllOrders() {
        return orderService.getAllOrders();
    }
}
