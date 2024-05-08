package com.electromart.electromart.dto;

import com.electromart.electromart.entity.Klarna;
import com.electromart.electromart.entity.Payment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public class KlarnaDTO extends PaymentDTO {

    private String klarnaFlag;
    private String emailAddress;

    public KlarnaDTO(){}

    public KlarnaDTO(String klarnaFlag, String emailAddress) {
        super();
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