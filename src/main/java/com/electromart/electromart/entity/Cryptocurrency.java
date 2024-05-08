package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Cryptocurrency")
@DiscriminatorValue(value = "Cryptocurrency")
public class Cryptocurrency extends Payment {

    @Column(name = "crypto_flag")
    private String cryptoFlag;

    @Column(name = "currency_name")
    private String currencyName;

    public Cryptocurrency() {}

    public Cryptocurrency(Order orderID, float paymentAmount, String paymentDate,
                 String paymentStatus, String cryptoFlag, String currencyName) {
        super(orderID, paymentAmount, paymentDate, paymentStatus);
        this.cryptoFlag = cryptoFlag;
        this.currencyName = currencyName;
    }

    public String getCryptoFlag() {
        return cryptoFlag;
    }

    public void setCryptoFlag(String cryptoFlag) {
        this.cryptoFlag = cryptoFlag;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}