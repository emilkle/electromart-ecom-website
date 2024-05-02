package com.electromart.electromart.controller;

import com.electromart.electromart.entity.ProductReview;
import com.electromart.electromart.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productReview")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @GetMapping("")
    public List<ProductReview> fetchAllProductReviews() {
        return productReviewService.getAllProductReviews();
    }
}
