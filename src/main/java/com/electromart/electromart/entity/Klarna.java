package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Klarna")
@DiscriminatorValue(value = "Klarna")
public class Klarna extends Payment {

    @Column(name = "klarna_flag")
    private String klarnaFlag;

    @Column(name = "email_address")
    private String emailAddress;

    public Klarna() {}

    public Klarna(Order orderID, float paymentAmount, String paymentDate,
                 String paymentStatus, String klarnaFlag, String emailAddress) {
        super(orderID, paymentAmount, paymentDate, paymentStatus);
        this.klarnaFlag = klarnaFlag;
        this.emailAddress = emailAddress;
    }

    public String getKlarnaFlag() {
        return klarnaFlag;
    }

    public void setKlarnaFlag(String klarnaFlag) {
        this.klarnaFlag = klarnaFlag;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}