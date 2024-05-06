package com.electromart.electromart.dto;

import com.electromart.electromart.entity.Order;

public class ShippingDTO {

    private int shippingID;
    private Order order;
    private String shippingMethod;
    private float shippingCost;

    public ShippingDTO () {}

    public ShippingDTO(int shippingID, Order order, String shippingMethod, float shippingCost) {
        this.shippingID = shippingID;
        this.order = order;
        this.shippingMethod = shippingMethod;
        this.shippingCost = shippingCost;
    }

    public int getShippingID() {
        return shippingID;
    }

    public void setShippingID(int shippingID) {
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
