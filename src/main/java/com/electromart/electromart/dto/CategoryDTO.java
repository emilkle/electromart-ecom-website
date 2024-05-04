package com.electromart.electromart.dto;

/**
 * Data Transfer Object for the (DTO) for category data.
 * It is used to encapsulate the category data that is transferred
   between the CategoryService and the CategoryController.
 * This ensures that the service layer are not directly interacting with the database.
 */
public class CategoryDTO {

    private Long categoryID;
    private String name;
    private String description;

    public CategoryDTO() {}

    public CategoryDTO(Long categoryID, String name, String description) {
        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryID;
    }

    public void setCategoryId(Long categoryId) {
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
}
