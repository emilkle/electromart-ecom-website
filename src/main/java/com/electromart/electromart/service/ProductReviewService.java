package com.electromart.electromart.service;

import com.electromart.electromart.dto.ProductReviewDTO;
import com.electromart.electromart.entity.Order;
import com.electromart.electromart.entity.Product;
import com.electromart.electromart.entity.ProductReview;
import com.electromart.electromart.entity.User;
import com.electromart.electromart.repository.ProductReviewRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service class responsible for handling product review-related operations.
 * Acts as an intermediary between the controller and the repository layer.
 */
@Service
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;

    /**
     * Constructor for ProductReviewService.
     *
     * @param productReviewRepository The repository for managing ProductReview entities.
     */
    public ProductReviewService(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    /**
     * Fetches all product reviews stored in the database.
     *
     * @return A list of ProductReviewDTO objects corresponding to all
     * product reviews in the database.
     */
    public List<ProductReviewDTO> getAllProductReviews() {
        List<ProductReview> productReviews = productReviewRepository.findAll();
        return productReviews.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Adds a new product review to the database using a ProductReviewDTO object.
     *
     * @param productReviewDTO The ProductReviewDTO object representing the
     *                         product review to be added.
     */
    public void addProductReview(ProductReviewDTO productReviewDTO) {
        ProductReview productReview = convertToEntity(productReviewDTO);
        productReviewRepository.save(productReview);
    }

    /**
     * Fetches a specific product review based on the review ID.
     *
     * @param id The review ID of the desired product review.
     * @return An Optional containing a ProductReviewDTO that matches the specified ID,
     * or an empty Optional if no product review is found.
     */
    public Optional<ProductReviewDTO> getProductReviewById(Long id) {
        Optional<ProductReview> optionalProductReview = productReviewRepository.findById(id);
        return optionalProductReview.map(this::convertToDTO);
    }

    /**
     * Deletes a product review from the database based on a specified review ID.
     *
     * @param id The review ID of the product review to be deleted.
     * @throws ResponseStatusException If no product review with the specified ID is found.
     */
    public void deleteProductReview(Long id) {
        if (productReviewRepository.existsById(id)) {
            productReviewRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No product review found with ID: " + id);
        }
    }

    /**
     * Converts a ProductReview entity object into a ProductReviewDTO data transfer object.
     *
     * @param productReview The ProductReview entity object to convert.
     * @return The converted ProductReviewDTO object.
     */
    private ProductReviewDTO convertToDTO(ProductReview productReview) {
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        BeanUtils.copyProperties(productReview, productReviewDTO);
        productReviewDTO.setUserID(productReview.getUser().getUserId());
        productReviewDTO.setProductID(productReview.getProduct().getProductId());
        return productReviewDTO;
    }

    /**
     * Converts a ProductReviewDTO data transfer object into a ProductReview entity object.
     *
     * @param productReviewDTO The ProductReviewDTO object to convert.
     * @return The converted ProductReview entity object.
     */
    private ProductReview convertToEntity(ProductReviewDTO productReviewDTO) {
        ProductReview productReview = new ProductReview();
        BeanUtils.copyProperties(productReviewDTO, productReview);

        // Add the userID from the productReviewDTO to the user.
        User user = new User();
        user.setUserId(productReviewDTO.getUserID());

        // Set the userID of the productReview to be the user's userID.
        productReview.setUser(user);
        productReview.getUser().setUserId(productReviewDTO.getUserID());

        // Add the productID from the productReviewDTO to the product.
        Product product = new Product();
        product.setProductId(productReviewDTO.getProductID());

        // Set the productID of the productReview to be the product's productID.
        productReview.setProduct(product);
        productReview.getProduct().setProductId(productReviewDTO.getProductID());

        // Return the productReview with the User and Product objects respectively containing
        // the input userID and productID.
        return productReview;
    }
}
