package com.electromart.electromart.dto;

/**
 * Data Transfer Object (DTO) for the inventory data.
 * It is used to encapsulate the inventory data that is transferred
 between the InventoryService and the InventoryController.
 * This ensures that the service layer are not directly interacting with the database.
 */
public class InventoryDTO {
    private Long inventoryID;
    //private Product product;
    private Long productID;
    private int quantity;
    private String lastRestockDate;

    public InventoryDTO() {}

    public InventoryDTO(Long inventoryID, Long productID, int quantity, String lastRestockDate) {
        this.inventoryID = inventoryID;
        this.productID = productID;
        this.quantity = quantity;
        this.lastRestockDate = lastRestockDate;
    }

    public Long getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(Long inventoryID) {
        this.inventoryID = inventoryID;
    }

    public Long getProduct() {
        return productID;
    }

    public void setProduct(Long product) {
        this.productID = product;
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
