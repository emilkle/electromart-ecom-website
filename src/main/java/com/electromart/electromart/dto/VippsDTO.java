package com.electromart.electromart.dto;

import com.electromart.electromart.entity.Payment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public class VippsDTO extends PaymentDTO {

    private String vippsFlag;
    private int phoneNumber;

    public VippsDTO() {}

    public VippsDTO(String vippsFlag, int phoneNumber) {
        super();
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