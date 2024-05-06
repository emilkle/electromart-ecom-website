package com.electromart.electromart.dto;

import com.electromart.electromart.entity.Product;

public class PromotionDTO {

    private Long promotionID;
    private Product product;
    private String description;
    private String discountType;
    private float discountvalue;
    private String startDate;
    private String endDate;

    public PromotionDTO() {}

    public PromotionDTO(Long promotionID, Product product, String description, String discountType, float discountvalue, String startDate, String endDate) {
        this.promotionID = promotionID;
        this.product = product;
        this.description = description;
        this.discountType = discountType;
        this.discountvalue = discountvalue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(Long promotionID) {
        this.promotionID = promotionID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public float getDiscountvalue() {
        return discountvalue;
    }

    public void setDiscountvalue(float discountvalue) {
        this.discountvalue = discountvalue;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
