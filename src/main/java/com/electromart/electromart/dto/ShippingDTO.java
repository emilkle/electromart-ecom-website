package com.electromart.electromart.dto;

import com.electromart.electromart.entity.Order;

public class ShippingDTO {

    private Long shippingID;
    private Long orderID;
    private String shippingMethod;
    private float shippingCost;

    public ShippingDTO () {}

    public ShippingDTO(Long shippingID, Long orderID, String shippingMethod, float shippingCost) {
        this.shippingID = shippingID;
        this.orderID = orderID;
        this.shippingMethod = shippingMethod;
        this.shippingCost = shippingCost;
    }

    public Long getShippingID() {
        return shippingID;
    }

    public void setShippingID(Long shippingID) {
        this.shippingID = shippingID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
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
