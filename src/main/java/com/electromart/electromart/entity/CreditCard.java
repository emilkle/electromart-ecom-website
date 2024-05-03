package com.electromart.electromart.entity;

import jakarta.persistence.*;

/*
@Entity
@Table(name="creditcard")
public class CreditCard extends Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long creditCardFlag;

    private String thirdPartService;

    public CreditCard() {}

    public CreditCard(long creditCardFlag, String thirdPartService) {
        super();
        this.creditCardFlag = creditCardFlag;
        this.thirdPartService = thirdPartService;
    }

    // Getter and setter for thirdPartService
    public String getThirdPartService() {
        return thirdPartService;
    }

    public void setThirdPartService(String thirdPartService) {
        this.thirdPartService = thirdPartService;
    }
}
 */


@Entity
@DiscriminatorValue("CREDITCARD")
public class CreditCard extends Payment {

    @Column(name = "credit_card_flag")
    private long creditCardFlag;

    @Column(name = "third_party_service")
    private String thirdPartyService;

    private String thirdPartService;

    public CreditCard() {}

    public CreditCard(long creditCardFlag, String thirdPartService) {
        super();
        this.creditCardFlag = creditCardFlag;
        this.thirdPartService = thirdPartService;
    }

    // Getter and setter for thirdPartService
    public String getThirdPartService() {
        return thirdPartService;
    }

    public void setThirdPartService(String thirdPartService) {
        this.thirdPartService = thirdPartService;
    }
}