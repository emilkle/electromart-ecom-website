package com.electromart.electromart.controller;

import com.electromart.electromart.dto.CategoryDTO;
import com.electromart.electromart.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

/**
 * RestController for managing categories in the database.
 * The controller utilize methods from the CategoryService to interact with the database.
 * It provides the application with endpoints used for HTTP GET, POST and DELETE requests,
   with corresponding endpoints for each request method.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    //@Autowired
    //private CategoryService categoryService;

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Handles GET requests for returning all the categories present in the database.
     * @return A list with all the categories in the database.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no categories are found.
     */
    @GetMapping("")
    public List<CategoryDTO> fetchAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No categories found");
        } else {
            return categories;
        }
    }

    /**
     * Handles POST requests for adding a new category to the database.
     * @param categoryDTO The categoryDTO representing the category to be added.
     * @return ResponseEntity containing the added categoryDTO and a http status code CREATED.
     */
    @PostMapping("")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        // Add the category to the database
        CategoryDTO savedCategory = categoryService.addCategory(categoryDTO);
        // Return a response with the saved category and HTTP status code
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    /**
     * Handles GET request for fetching a specific category by its ID, from the database.
     * If category is not found, the method returns a message informing about this.
     * @param id The categoryID of the desired category.
     * @return ResponseEntity containing the categoryDTO if found and HTTP status OK, otherwise NOT_FOUND.
     */
    @GetMapping("/{id}")
    // Return type set to "?" for flexible return type in case the desired category does not exist and
    // a category not found message needs to be returned.
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        // Get the category
        Optional<CategoryDTO> optionalCategory = categoryService.getCategoryById(id);
        // Check if the specified category exists
        if (optionalCategory.isPresent()) {
            //Return the category and HTTP status code OK
            return new ResponseEntity<>(optionalCategory.get(), HttpStatus.OK);
        } else {
            // If the category does not exist, return HTTP status code NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with ID: '" + id + "' not found");
        }
    }

    /**
     * Deletes a category based on a specified categoryID, and provides a feedback on the outcome.
     * @param id The categoryID of the category to be deleted.
     * @return ResponseEntity containing the outcome of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().body("Category with ID: '" + id + "' successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found with ID: " + id);
        }
    }
}