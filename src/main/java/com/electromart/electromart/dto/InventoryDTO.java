package com.electromart.electromart.dto;

import com.electromart.electromart.entity.Product;

/**
 * Data Transfer Object for the (DTO) for inventory data.
 * It is used to encapsulate the inventory data that is transferred
 between the InventoryService and the InventoryController.
 * This ensures that the service layer are not directly interacting with the database.
 */
public class InventoryDTO {
    private int inventoryID;
    private Product product;
    private int quantity;
    private String lastRestockDate;

    public InventoryDTO() {}

    public InventoryDTO(int inventoryID, Product product, int quantity, String lastRestockDate) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantity = quantity;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLastRestockDate() {
        return lastRestockDate;
    }

    public void setLastRestockDate(String lastRestockDate) {
        this.lastRestockDate = lastRestockDate;
    }
}
