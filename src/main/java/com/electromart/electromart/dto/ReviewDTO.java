package com.electromart.electromart.dto;

import com.electromart.electromart.entity.Product;
import com.electromart.electromart.entity.ProductReview;
import com.electromart.electromart.entity.User;

public class ReviewDTO {

    private int reviewID;
    private User user;
    private Product product;
    private int rating;
    private String reviewText;
    private String reviewDate;

    public ReviewDTO () {}

    public ReviewDTO(int reviewID, User user, Product product, int rating, String reviewText, String reviewDate) {
        this.reviewID = reviewID;
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
