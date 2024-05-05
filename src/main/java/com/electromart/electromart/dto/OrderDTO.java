package com.electromart.electromart.dto;

/**
 * Data Transfer Object for the (DTO) for order data.
 * It is used to encapsulate the order data that is transferred
 between the OrderService and the OrderController.
 * This ensures that the service layer are not directly interacting with the database.
 */
public class OrderDTO {

    private int orderId;
    private int customerId;
    private int productId;
    private int quantity;
    private double price;

    public OrderDTO() {}

    public OrderDTO(int orderId, int customerId, int productId, int quantity, double price) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
