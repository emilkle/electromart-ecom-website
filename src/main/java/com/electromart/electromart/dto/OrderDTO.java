package com.electromart.electromart.dto;

import com.electromart.electromart.entity.User;

/**
 * Data Transfer Object for the (DTO) for order data.
 * It is used to encapsulate the order data that is transferred
 between the OrderService and the OrderController.
 * This ensures that the service layer are not directly interacting with the database.
 */
public class OrderDTO {

    private Long orderID;
    private Long userID;
    private String orderDate;
    private float orderTotalAmount;
    private String orderStatus;

    //private int productID;
    //private int quantity;
    //private double price;

    public OrderDTO() {}

    public OrderDTO(Long orderId, Long userId, String orderDate, float orderAmount, String orderStatus) {
        this.orderID = orderId;
        this.userID = userId;
        this.orderDate = orderDate;
        this.orderTotalAmount = orderAmount;
        this.orderStatus = orderStatus;
    }

    /*
    public OrderDTO(Long orderId, Long userId, int productId, int quantity, double price) {
        this.orderID = orderId;
        this.userID = userId;
        this.productID = productId;
        this.quantity = quantity;
        this.price = price;
    }
     */

    public Long getOrderId() {
        return orderID;
    }

    public void setOrderId(Long orderId) {
        this.orderID = orderId;
    }

    public Long getUserId() {
        return userID;
    }

    public void setUserId(Long customerId) {
        this.userID = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public float getOrderAmount() {
        return orderTotalAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderTotalAmount = orderAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /*
    public int getProductId() {
        return productID;
    }

    public void setProductId(int productId) {
        this.productID = productId;
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
     */
}
