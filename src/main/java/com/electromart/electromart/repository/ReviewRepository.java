package com.electromart.electromart.repository;

import com.electromart.electromart.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ProductReview, Long> {
}
