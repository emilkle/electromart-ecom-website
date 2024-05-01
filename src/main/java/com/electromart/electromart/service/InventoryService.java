package com.electromart.electromart.service;

import com.electromart.electromart.entity.Inventory;
import com.electromart.electromart.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    /**
     * Method for updating inventory quantity of a product after a purchase.
     */
    public void updateInventoryAfterPurchase (long productID, int purchaseQuantity) throws Exception {
        Inventory product = inventoryRepository.findById(productID)
                .orElseThrow(()-> new Exception("Product not found"));

        int currentStock = product.getQuantity();
        int updatedStock = currentStock - purchaseQuantity;

        if (updatedStock < 0) {
            throw new Exception("Product has not enough stock items. Refill inventory.");
        }
        product.setQuantity(updatedStock);
        inventoryRepository.save(product);
    }
}
