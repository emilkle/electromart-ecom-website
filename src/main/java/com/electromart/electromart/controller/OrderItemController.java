package com.electromart.electromart.controller;

import com.electromart.electromart.entity.OrderItem;
import com.electromart.electromart.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("")
    public List<OrderItem> fetchOrderItems() {
        return orderItemService.getAllOrderItems();
    }
}
