package com.electromart.electromart.dto;

/**
 * Data Transfer Object for the (DTO) for brand data.
 * It is used to encapsulate the brand data that is transferred
 between the BrandService and the BrandController.
 * This ensures that the service layer are not directly interacting with the database.
 */
public class BrandDTO {

    private Long brandID;
    private String name;
    private String description;

    public BrandDTO() {}

    public BrandDTO(Long brandID, String name, String description) {
        this.brandID = brandID;
        this.name = name;
        this.description = description;
    }

    public Long getBrandID() {
        return brandID;
    }

    public void setBrandID(Long brandID) {
        this.brandID = brandID;
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

}
