package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Vipps")
@DiscriminatorValue(value="Vipps")
public class Vipps extends Payment {

    @Column(name = "vipps_flag")
    private String vippsFlag;

    @Column(name = "phone_number")
    private int phoneNumber;

    public Vipps() {}

    public Vipps(Order orderID, float paymentAmount, String paymentDate,
                      String paymentStatus, String vippsFlag, int phoneNumber) {
        super(orderID, paymentAmount, paymentDate, paymentStatus);
        this.vippsFlag = vippsFlag;
        this.phoneNumber = phoneNumber;
    }

    public String getVippsFlag() {
        return vippsFlag;
    }

    public void setVippsFlag(String vippsFlag) {
        this.vippsFlag = vippsFlag;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}