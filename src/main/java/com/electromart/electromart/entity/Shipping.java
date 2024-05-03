package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String shippingID;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order orderID;

    private String shippingMethod;
    private float shippingCost;

    public Shipping() {}

    public Shipping(Order orderID, String shippingMethod, float shippingCost){
        this.orderID = orderID;
        this.shippingMethod = shippingMethod;
        this.shippingCost = shippingCost;
    }

    public String getShippingID() {
        return shippingID;
    }

    public void setShippingID(String shippingID) {
        this.shippingID = shippingID;
    }

    public Order getOrderID() {
        return orderID;
    }

    public void setOrderID(Order orderID) {
        this.orderID = orderID;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(float shippingCost) {
        this.shippingCost = shippingCost;
    }
}

