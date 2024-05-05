package com.electromart.electromart.entity;

import jakarta.persistence.*;

/**
 * The Order class represents the order entities that are stored in the database.
 */
@Entity
@Table(name="`Order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_ID")
    private Long orderID;

    @OneToOne
    @JoinColumn(name = "user_ID")
    private User userID;

    private String orderDate;
    private float orderTotalAmount;
    private String orderStatus;

    public Order () {}

    public Order (User userID, String orderDate, float orderTotalAmount, String orderStatus) {
        this.userID = userID;
        this.orderDate = orderDate;
        this.orderTotalAmount = orderTotalAmount;
        this.orderStatus = orderStatus;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderTotalAmount(float orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public float getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

