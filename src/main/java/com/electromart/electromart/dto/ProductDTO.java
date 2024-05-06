package com.electromart.electromart.dto;

import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.entity.Category;

public class ProductDTO {
    private long productID;
    private Brand brand;
    private Category category;
    private String name;
    private String description;
    private float price;

    public ProductDTO () {}

    public ProductDTO(long productID, Brand brand, Category category, String name, String description, float price) {
        this.productID = productID;
        this.brand = brand;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
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
