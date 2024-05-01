package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_ID;

    public void setOrder_ID(Long orderId) {
        this.order_ID = orderId;
    }

    public Long getOrder_ID() {
        return order_ID;
    }
}
