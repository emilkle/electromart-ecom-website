package com.electromart.electromart.service;

import com.electromart.electromart.entity.ProductReview;
import com.electromart.electromart.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public List<ProductReview> getAllProductReviews() {
        return productReviewRepository.findAll();
    }
}
