package com.electromart.electromart.dto;

public class CryptocurrencyDTO extends PaymentDTO {

    private String cryptoFlag;
    private String currencyName;

    public CryptocurrencyDTO(){}

    public CryptocurrencyDTO(String cryptoFlag,String currencyName) {
        super();
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