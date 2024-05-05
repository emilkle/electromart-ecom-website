package com.electromart.electromart.service;

import com.electromart.electromart.dto.CategoryDTO;
import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.entity.Category;
import com.electromart.electromart.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing categories within the application.
 * It provides methods that is used by the CategoryController for fetching and adding categories to the database.
 * It also handles the conversions between category DTOs and entities.
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Fetches all categories that is stored in the database.
     *
     * @return a list of categoryDTO objects corresponding to all the categories that are stored in the database.
     */
    public List<CategoryDTO> getAllCategories() {
        // Fetches all the categories and store them in a list.
        List<Category> categories = categoryRepository.findAll();
        // Goes through the list of categories and converts each category object to categoryDTO objects.
        // Then it collect and return all the converted categories in a list.
        return categories.stream()
            .map(category -> convertToDTO(category))
            .collect(Collectors.toList());
    }

    /**
     * Adds a new category to the database by using a categoryDTO object.
     *
     * @param categoryDTO The categoryDTO object representing the category to be added.
     * @return A categoryDTO representation of the added category.
     */
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        // Create new category object based on the provided categoryDTO.
        Category category = convertToEntity(categoryDTO);
        // Save this category in the database.
        Category savedCategory = categoryRepository.save(category);
        // Return the newly added category as a categoryDTO object.
        return convertToDTO(savedCategory);
    }

    /**
     * Fetches a specific categoryDTO based on the categoryID.
     *
     * @param id The categoryID of the desired category.
     * @return A categoryDTO that matches the specified categoryID,
     * or an empty optional if no category with the specified categoryID was found.
     */
    public Optional<CategoryDTO> getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.map(category -> convertToDTO(category));
    }

    /**
     * Deletes a category object from the database based on a specified categoryID.
     *
     * @param id The specified categoryID of the category to be deleted.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no categories with the specified ID are found.
     */
    public void deleteCategory(Long id) {
        // Check if any category with the specified ID exist.
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Category not found with ID: " + id);
        }
    }

    /**
     * Retrieves the description of a category by its name from the repository.
     *
     * @param name the name of the category to retrieve the description for
     * @return an Optional containing the description of the category if found,
     *         or an empty Optional if the category is not found
     */
    public Optional<String> getDescriptionFromName(String name) {
        // Retrieves a category with the specified name from the repository
        Optional<Category> categoryParam = categoryRepository.findAll()
            .stream()
            .filter(category -> category.getName().equals(name))
            .findFirst();

        // Maps the category to its description if found
        return categoryParam.map(Category::getDescription);
    }


    /**
     * Converts categoryDTO object into a category entity object.
     * @param category The category object to convert.
     * @return  The converted category object.
     */
    private CategoryDTO convertToDTO(Category category) {
        // Create new categoryDTO object to store the converted category object
        CategoryDTO categoryDTO = new CategoryDTO();
        // Using BeanUtils library for copying the values in the category to the categoryDTO.
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }

    /**
     * Converts categoryDTO object into a category entity object.
     * @param categoryDTO The category data transfer object to convert.
     * @return The converted category object.
     */
    private Category convertToEntity(CategoryDTO categoryDTO) {
        // Create new category object to store the converted categoryDTO object
        Category category = new Category();
        // Using BeanUtils library for copying the values in the categoryDTO to the category.
        BeanUtils.copyProperties(categoryDTO, category);
        return category;
    }
}