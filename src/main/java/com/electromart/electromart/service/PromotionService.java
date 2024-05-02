package com.electromart.electromart.service;

import com.electromart.electromart.entity.Product;
import com.electromart.electromart.entity.Promotion;
import com.electromart.electromart.repository.InventoryRepository;
import com.electromart.electromart.repository.ProductRepository;
import com.electromart.electromart.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Promotion> getAllPromotions () {
        return promotionRepository.findAll();
    }

    /**
     * This metohd calculates the price discount for a promotion
     */
    public float calculateDiscountPrice (Product product, Promotion promotion) {
        float originalPrice = product.getPrice();
        float discountvalue = promotion.getDiscountValue();

        if (promotion.getDiscountType().equals("percentage")) {
            return originalPrice * (1-discountvalue/100);
        } else if (Objects.equals(promotion.getDiscountType(), "fixed")) {
            return originalPrice - discountvalue;
        } else{
            throw new IllegalArgumentException("Invalid discount type");
        }
    }

    /**
     * startPromotionAndUpdatePrice creates a new promotion and updates the corresponding product with the correct
     * discounted price
     * @param productID a products id
     * @param description description of a promotion
     * @param discountType type of discount, percentage or fixed
     * @param discountValue amount of discount
     * @param startDate promotion start date
     * @param endDate promotion end date
     */
    public void startPromotionAndUpdatePrice (Product productID, String description, String discountType, int discountValue, String startDate, String endDate) {
        Promotion promotion = new Promotion(productID, description, discountType, discountValue, startDate, endDate);
        promotionRepository.save(promotion);
        float discountPrice = calculateDiscountPrice(productID,promotion);
        productID.setPrice(discountPrice);
        productRepository.save(productID);
    }

}
