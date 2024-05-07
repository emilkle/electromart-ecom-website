package com.electromart.electromart.controller;

import com.electromart.electromart.dto.ProductReviewDTO;
import com.electromart.electromart.entity.ProductReview;
import com.electromart.electromart.service.ProductReviewService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller class responsible for handling product review-related HTTP requests.
 */
@RestController
@RequestMapping("/productReview")
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    /**
     * Constructor for ProductReviewController.
     *
     * @param productReviewService The service responsible for handling product
     *                             review-related operations.
     */
    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    /**
     * Handles GET requests to retrieve all product reviews.
     *
     * @return A list of ProductReviewDTO objects representing all product reviews.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no product reviews are found.
     */
    @GetMapping({"", "/"})
    public List<ProductReviewDTO> fetchAllProductReviews() {
        List<ProductReviewDTO> productReviewList = productReviewService.getAllProductReviews();
        if (productReviewList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product reviews found.");
        } else {
            return productReviewList;
        }
    }

    /**
     * Handles GET requests to retrieve a specific product review by ID.
     *
     * @param id The ID of the product review to retrieve.
     * @return ResponseEntity with the ProductReviewDTO object if found, or NOT_FOUND status with
     * a message if not found.
     */
    @GetMapping("/review_id={id}")
    public ResponseEntity<?> getProductReviewById(@PathVariable Long id) {
        Optional<ProductReviewDTO> optionalProductReview =
            productReviewService.getProductReviewById(id);
        if (optionalProductReview.isPresent()) {
            return new ResponseEntity<>(optionalProductReview.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Product review with ID: '" + id + "' not found.");
        }
    }

    /**
     * Handles POST requests to add a new product review.
     *
     * @param productReviewRequest The ProductReviewDTO object representing the new product review.
     * @return ResponseEntity with a success message and CREATED status.
     */
    @PostMapping({"", "/"})
    public ResponseEntity<String> addNewProductReview(@RequestBody
                                                          ProductReviewDTO productReviewRequest) {
        productReviewService.addProductReview(productReviewRequest);
        return new ResponseEntity<>(
            "The requested product review was created successfully.", HttpStatus.CREATED);
    }
}
