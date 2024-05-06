package com.electromart.electromart.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="promotion_ID")
    private Long promotionID;

    @ManyToOne
    @JoinColumn(name="product_ID")
    private Product product;
    private String description;
    private String discountType;
    private float discountValue;
    private Date startDate;
    private Date endDate;

    public Promotion () {}

    public Promotion(Product product, String description, String discountType, int discountValue,
                     Date startDate, Date endDate) {
        this.product = product;
        this.description = description;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(long promotionID) {
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

    public float getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(float discountValue) {
        this.discountValue = discountValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
