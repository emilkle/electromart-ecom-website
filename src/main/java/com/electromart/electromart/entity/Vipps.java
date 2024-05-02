package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="vipps")
public class Vipps extends Payment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vippsFlag;

    private int phoneNumber;

    public Vipps() {}

    public Vipps(long vippsFlag, int phoneNumber) {
        super();
        this.vippsFlag = vippsFlag;
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
