package com.electromart.electromart.dto;

/**
 * Data Transfer Object (DTO) for the product data.
 * It is used to encapsulate the product data that is transferred
 between the ProductService and the ProductController.
 * This ensures that the service layer are not directly interacting with the database.
 */
public class ProductDTO {
    private Long productID;
    //private Brand brand;
    private Long brandID;
    //private Category category;
    private Long categoryID;
    private String name;
    private String description;
    private float price;

    public ProductDTO () {}

    public ProductDTO(Long productID, Long brandId, Long categoryId, String name, String description, float price) {
        this.productID = productID;
        this.brandID = brandId;
        this.categoryID = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Long getBrandID() {
        return brandID;
    }

    public void setBrandID(Long brandId) {
        this.brandID = brandId;
    }

    public Long getCategory() {
        return categoryID;
    }

    public void setCategory(Long categoryId) {
        this.categoryID = categoryId;
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
