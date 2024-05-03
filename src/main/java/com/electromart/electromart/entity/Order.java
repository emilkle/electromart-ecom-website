package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderID;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userID;

    private String orderDate;
    private float orderTotalAmount;
    private String orderStatus;

    public Order () {}

    public Order (String orderID, User userID, String orderDate, float orderTotalAmount, String orderStatus) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.orderTotalAmount = orderTotalAmount;
        this.orderStatus = orderStatus;
    }

    public float getOrderTotalAmount() {
        return orderTotalAmount;
    }
}

