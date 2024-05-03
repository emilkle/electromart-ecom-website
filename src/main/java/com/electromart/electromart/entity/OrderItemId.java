package com.electromart.electromart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderItemId implements Serializable {
    @Column(name="order_ID")
    private int orderID;

    @Column(name="product_ID")
    private int productID;
}
