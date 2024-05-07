package com.electromart.electromart.controller;

import com.electromart.electromart.dto.ProductDTO;
import com.electromart.electromart.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Handles GET requests for returning all the products present in the database.
     * @return A list with all the products in the database.
     * @throws ResponseStatusException with HttpStatus. OK with message if no products are found.
     */
    @GetMapping({"/"})
    public ResponseEntity<?> fetchAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("The system does not contain any products.");
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    /**
     * Handles POST requests for adding a new product to the database.
     * @param productDTO The productDTO representing the product to be added.
     * @return ResponseEntity containing the added productDTO and a http status code CREATED.
     */
    @PostMapping("/")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        // Add the product to the database
        ProductDTO savedProduct = productService.addProduct(productDTO);
        // Return a response with the saved product and HTTP status code
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    /**
     * Handles GET request for fetching a specific product by its ID, from the database.
     * If product is not found, the method returns a message informing about this.
     * @param id The productID of the desired product.
     * @return ResponseEntity containing the productDTO if found and HTTP status OK, otherwise OK and message.
     */
    @GetMapping("/category_id={id}")
    // Return type set to "?" for flexible return type in case the desired product does not exist and
    // a product not found message needs to be returned.
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        // Get the product
        Optional<ProductDTO> optionalProduct = productService.getProductById(id);
        // Check if the specified product exists
        if (optionalProduct.isPresent()) {
            //Return the product and HTTP status code OK
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        } else {
            // If the product does not exist, return HTTP status code OK and message
            return ResponseEntity.status(HttpStatus.OK).body("Product with ID: '" + id + "' not found");
        }
    }

    /**
     * Retrieves the description of a product by its name.
     *
     * @param name the name of the product
     * @return a ResponseEntity containing the description of the product if found,
     *         or an error response if the category is not found or the name parameter is empty
     */
    @GetMapping("/name={name}")
    public ResponseEntity<String> getProductDescription(@PathVariable(value = "name",
            required = false) String name) {
        // Retrieves the description of the product based on its name
        Optional<String> productNameParam = productService.getDescriptionFromName(name);

        // Checks if a valid product name is provided
        if (!name.isEmpty()) {
            // Returns the product description if found, or a not found response otherwise
            return productNameParam.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.OK)
                            .body("The product with name " + name + " was not found"));
        } else {
            // Returns a bad request response if no valid product name is specified
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No valid product name was specified. To add a new product " +
                            "use the HTTP POST method.");
        }
    }

    /**
     * Deletes a category based on a specified productID, and provides a feedback on the outcome.
     * @param id The productID of the product to be deleted.
     * @return ResponseEntity containing the outcome of the deletion.
     */
    @DeleteMapping("/delete-category/category_id={id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().body("Product with ID: '" + id + "' successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body("Product not found with ID: " + id);
        }
    }
}
