package com.electromart.electromart.entity;

import jakarta.persistence.*;

/**
 * The Product class represents the product entities that are stored in the database.
 */
@Entity
@Table(name="Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_ID")
    private Long productID;

    @ManyToOne
    @JoinColumn(name = "brand_ID")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_ID")
    private Category category;

    private String name;

    private String description;

    private float price;

    public Product () {

    }

    public Product (Brand brand, Category category) {
        this.brand = brand;
        this.category = category;
        this.name = "";
        this.description = "";
        this.price = 0;
    }

    public Long getProductId() {
        return productID;
    }

    public void setProductId(Long productId) {
        this.productID = productId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

