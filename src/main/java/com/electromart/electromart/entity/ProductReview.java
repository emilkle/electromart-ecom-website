package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewID;

    @OneToOne
    @JoinColumn(name="user_ID")
    private User userID;

    @OneToOne
    @JoinColumn(name="product_ID")
    private Product productID;

    private int rating;

    private String reviewText;

    private String reviewDate;

    public ProductReview() {}

    public ProductReview(User userID, Product productID, int rating, String reviewText, String reviewDate) {
        this.userID = userID;
        this.productID = productID;
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
