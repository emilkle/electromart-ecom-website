package com.electromart.electromart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 * OrderItemId class represents the composite key in for the OrderItem class.
 * It consists of orderID and productID to identify a unique ID for orderItems.
 */
@Embeddable
public class OrderItemId implements Serializable {
    @Column(name="order_ID")
    private Long orderID;

    @Column(name="product_ID")
    private Long productID;

    public OrderItemId() {}

    public OrderItemId(Long orderID, Long productID) {
        this.orderID = orderID;
        this.productID = productID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }
}