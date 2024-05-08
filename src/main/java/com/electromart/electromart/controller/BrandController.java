package com.electromart.electromart.controller;

import com.electromart.electromart.dto.BrandDTO;
import com.electromart.electromart.dto.CategoryDTO;
import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.entity.Product;
import com.electromart.electromart.repository.BrandRepository;
import com.electromart.electromart.repository.ProductRepository;
import com.electromart.electromart.service.BrandService;
import com.electromart.electromart.service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Brand controller.
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    BrandController(BrandService brandService, BrandRepository repository,
                    ProductRepository productRepository, ProductService productService) {
        this.brandRepository = repository;
        this.brandService = brandService;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    /**
     * Handles GET requests for returning all the brands present in the database.
     * @return A list with all the brands in the database.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no brands are found.
     */
    @GetMapping({"", "/"})
    public List<BrandDTO> fetchAllCategories() {
        List<BrandDTO> brands = brandService.getAllBrands();
        if (brands.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No brands found");
        } else {
            return brands;
        }
    }

    /**
     * Handles GET request for fetching a specific brand by its ID, from the database.
     * If brand is not found, the method returns a message informing about this.
     * @param id The brandID of the desired brand.
     * @return ResponseEntity containing the brandDTO if found and HTTP status OK, otherwise NOT_FOUND.
     */
    @GetMapping("/brand_id={id}")
    public ResponseEntity<?> getBrandById(@PathVariable Long id) {
        // Get the brand
        Optional<BrandDTO> optionalBrand = brandService.getBrandById(id);
        // Check if the specified brand exists
        if (optionalBrand.isPresent()) {
            //Return the brand and HTTP status code OK
            return new ResponseEntity<>(optionalBrand.get(), HttpStatus.OK);
        } else {
            // If the brand does not exist, return HTTP status code NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand with ID: " +
                "'" + id + "' not found");
        }
    }

    /**
     * Fetch brand description response entity.
     *
     * @param name the name
     * @return the response entity
     */
    @GetMapping("/name={name}")
    public ResponseEntity<String> fetchBrandDescription(@PathVariable(
        value = "name", required = false) String name) {
        Optional<String> brandNameParam = brandService.getDescriptionFromName(name);
        if (!name.isEmpty()) {
            return brandNameParam.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The brand with name " + name + " Was not found"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No valid brand name was specified. To add a new brand " +
                    "use the HTTP POST method.");
        }
    }

    /**
     * Add new brand response entity.
     *
     * @param brandRequest the brand request
     * @return the response entity
     */
    @PostMapping({"", "/"})
    public ResponseEntity<String> addNewBrand(@RequestBody BrandDTO brandRequest) {
            // Add the brand to the database
            brandService.addBrand(brandRequest);
            // Return a response with the saved brand and HTTP status code
            return new ResponseEntity<>("The requested brand was " +
                "created successfully.", HttpStatus.CREATED);
    }

    /**
     * Delete brand response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/brand_id={id}")
    public ResponseEntity<String> deleteBrand(@PathVariable(
        value = "id", required = false) Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID cannot be null.");
        }

        if (brandRepository.existsById(id)) {
            try {
                List<Product> products = productService.findProductFromBrandId(id);
                for (Product product : products) {
                    productRepository.deleteById(product.getProductId());
                }
                brandRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("The requested brand was " +
                        "deleted successfully.");
            } catch (EmptyResultDataAccessException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Brand with ID " + id + " not found.");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cannot delete brand because it has associated products.");
            } catch (MethodArgumentTypeMismatchException e){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid ID." +
                    " Please use an integer number");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Brand with ID " + id + " does not exist.");
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Invalid JSON payload. Please ensure that the data types are correct.");
    }
}
