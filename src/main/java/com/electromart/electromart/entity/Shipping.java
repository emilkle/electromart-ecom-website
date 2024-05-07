package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shipping_ID")
    private Long shippingID;

    @OneToOne
    @JoinColumn(name = "order_ID")
    private Order order;

    private String shippingMethod;
    private float shippingCost;

    public Shipping() {}

    public Shipping(Order order, String shippingMethod, float shippingCost){
        this.order = order;
        this.shippingMethod = shippingMethod;
        this.shippingCost = shippingCost;
    }

    public Long getShippingID() {
        return shippingID;
    }

    public void setShippingID(Long shippingID) {
        this.shippingID = shippingID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

