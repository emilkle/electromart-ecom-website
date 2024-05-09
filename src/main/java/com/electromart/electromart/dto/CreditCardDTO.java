package com.electromart.electromart.dto;

public class CreditCardDTO extends PaymentDTO {

    private String creditCardFlag;
    private String thirdPartService;

    public CreditCardDTO(){

    }

    public CreditCardDTO(String creditCardFlag, String thirdPartService) {
        super();
        this.creditCardFlag = creditCardFlag;
        this.thirdPartService = thirdPartService;
    }

    public String getCreditCardFlag() {
        return creditCardFlag;
    }

    public void setCreditCardFlag(String creditCardFlag) {
        this.creditCardFlag = creditCardFlag;
    }

    public String getThirdPartService() {
        return thirdPartService;
    }

    public void setThirdPartService(String thirdPartService) {
        this.thirdPartService = thirdPartService;
    }
}