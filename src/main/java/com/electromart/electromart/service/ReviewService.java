package com.electromart.electromart.service;

import com.electromart.electromart.entity.Product;
import com.electromart.electromart.entity.ProductReview;
import com.electromart.electromart.entity.User;
import com.electromart.electromart.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ProductReview> getAllReviews () {
        return reviewRepository.findAll();
    }


    /**
     * createProductReview lets a user create a new review of a product
     * @param productID id of the given product
     * @param userID id of the given user
     * @param rating rating of the given product
     * @param reviewText review text of the given product
     */
    public void createProductReview (Product productID, User userID, int rating, String reviewText, String reviewDate) {
        // user validation?
        // product validation?
        ProductReview review = new ProductReview(userID, productID, rating, reviewText, reviewDate);
    }
}
