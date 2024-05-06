package com.electromart.electromart.controller;

import com.electromart.electromart.dto.CategoryDTO;
import com.electromart.electromart.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
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

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Handles GET requests for returning all the categories present in the database.
     * @return A list with all the categories in the database.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no categories are found.
     */
    @GetMapping({""})
    public ResponseEntity<?> fetchAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("The system does not contain any categories.");
        } else {
            return new ResponseEntity<>(categories, HttpStatus.OK);
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
    @GetMapping("/category_id={id}")
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
     * Retrieves the description of a category by its name.
     *
     * @param name the name of the category
     * @return a ResponseEntity containing the description of the category if found,
     *         or an error response if the category is not found or the name parameter is empty
     */
    @GetMapping("/name={name}")
    public ResponseEntity<String> getCategoryDescription(@PathVariable(value = "name",
        required = false) String name) {
        // Retrieves the description of the category based on its name
        Optional<String> categoryNameParam = categoryService.getDescriptionFromName(name);

        // Checks if a valid category name is provided
        if (!name.isEmpty()) {
            // Returns the category description if found, or a not found response otherwise
            return categoryNameParam.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The category with name " + name + " was not found"));
        } else {
            // Returns a bad request response if no valid category name is specified
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No valid category name was specified. To add a new category " +
                    "use the HTTP POST method.");
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