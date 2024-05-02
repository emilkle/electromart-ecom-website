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

    /**
     *
     * @param productID externally supplied productID for checking inventory amount
     * @param shoppingCartAmount externally supplied amount to check against inventoryAmount
     * @throws Exception productID not found, shoppingCartAmount negative or zero.
     */
    public boolean checkInventoryQuantity(long productID, int shoppingCartAmount) throws Exception {

        if (shoppingCartAmount <= 0) {
            throw new Exception("Invalid number supplied.");
        }
        Inventory product = inventoryRepository.findById(productID)
                .orElseThrow(()-> new Exception("Product not found"));

        int productStockQuantity = product.getQuantity();

        return (shoppingCartAmount<=productStockQuantity);

    }


}
