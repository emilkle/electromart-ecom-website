package com.electromart.electromart.entities;

import jakarta.persistence.*;

@Entity
@Table(name="shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String shippingID;

    @JoinColumn(name = "order_id")
    private Order orderID;

    private String shippingMethod;
    private String shippingCost;

    public Shipping() {}

    public Shipping(String shippingMethod, String shippingCost){
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

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }
}