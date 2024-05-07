package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_ID")
    private Long reviewID;

    @OneToOne
    @JoinColumn(name="user_ID")
    private User user;

    @OneToOne
    @JoinColumn(name="product_ID")
    private Product product;

    private int rating;

    private String reviewText;

    private String reviewDate;

    public ProductReview() {}

    public ProductReview(User user, Product product, int rating,
                         String reviewText, String reviewDate) {
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }

    public Long getReviewID() {
        return reviewID;
    }

    public void setReviewID(Long reviewID) {
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
