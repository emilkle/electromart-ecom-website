package com.electromart.electromart.controller;

import com.electromart.electromart.entity.Inventory;
import com.electromart.electromart.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("")
    public List<Inventory> fetchAllInventory() {
        return inventoryService.getAllInventory();
    }
}
