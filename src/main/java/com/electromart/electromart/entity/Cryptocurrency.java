package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="cryptocurrency")
public class Cryptocurrency extends Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cryptoFlag;

    private String currencyName;

    public Cryptocurrency() {}

    public Cryptocurrency(long cryptoFlag ,String currencyName) {
        super();
        this.cryptoFlag = cryptoFlag;
        this.currencyName = currencyName;
    }


    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
