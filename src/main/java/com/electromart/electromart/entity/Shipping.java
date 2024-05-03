package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shippingID;

    @OneToOne
    @JoinColumn(name = "order_ID")
    private Order orderID;

    private String shippingMethod;
    private float shippingCost;

    public Shipping() {}

    public Shipping(Order orderID, String shippingMethod, float shippingCost){
        this.orderID = orderID;
        this.shippingMethod = shippingMethod;
        this.shippingCost = shippingCost;
    }

    public int getShippingID() {
        return shippingID;
    }

    public void setShippingID(int shippingID) {
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

