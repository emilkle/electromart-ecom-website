package com.electromart.electromart.entities;

import jakarta.persistence.*;

@Entity
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userID;
    
    private String orderDate;
    private String orderTotalAmount;
    private String orderStatus;
    
    public Order () {}
    
    public Order (int orderID, User userID, String orderDate, String orderTotalAmount, String orderStatus) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.orderTotalAmount = orderTotalAmount;
        this.orderStatus = orderStatus;
    }
}