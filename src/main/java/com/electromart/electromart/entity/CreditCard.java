package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "creditcard")
@DiscriminatorValue(value = "Credit card")
public class CreditCard extends Payment {

    @Column(name = "credit_card_flag")
    private String creditCardFlag;

    @Column(name = "third_party_service")
    private String thirdPartyService;

    public CreditCard() {}

    public CreditCard(Order orderID, float paymentAmount, String paymentDate,
                      String paymentStatus, String creditCardFlag, String thirdPartyService) {
        super(orderID, paymentAmount, paymentDate, paymentStatus);
        this.creditCardFlag = creditCardFlag;
        this.thirdPartyService = thirdPartyService;
    }

    public String getCreditCardFlag() {
        return creditCardFlag;
    }

    public void setCreditCardFlag(String creditCardFlag) {
        this.creditCardFlag = creditCardFlag;
    }

    public String getThirdPartService() {
        return thirdPartyService;
    }

    public void setThirdPartService(String thirdPartService) {
        this.thirdPartyService = thirdPartService;
    }
}