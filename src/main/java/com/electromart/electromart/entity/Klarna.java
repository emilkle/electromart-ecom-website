package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="klarna")
public class Klarna extends Payment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long klarnaFlag;

    private String emailAddress;

    public Klarna() {}

    public Klarna(long klarnaFlag, String emailAddress) {
        super();
        this.klarnaFlag = klarnaFlag;
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
