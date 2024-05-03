package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="payment")
public abstract class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentID;

    @JoinColumn(name="order_id")
    @OneToOne(mappedBy = "order_id")
    private Order orderID;

    private String paymentType;
    private float paymentAmount;
    private String paymentDate;
    private String paymentStatus;

    public long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(long paymentID) {
        this.paymentID = paymentID;
    }

    public Order getOrderID() {
        return orderID;
    }

    public void setOrderID(Order orderID) {
        this.orderID = orderID;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}