package com.electromart.electromart.service;

import com.electromart.electromart.dto.PromotionDTO;
import com.electromart.electromart.entity.Product;
import com.electromart.electromart.entity.Promotion;
import com.electromart.electromart.repository.ProductRepository;
import com.electromart.electromart.repository.PromotionRepository;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PromotionService {


    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    public PromotionService(PromotionRepository promotionRepository,
                            ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.productRepository = productRepository;
    }

    /**
     * Fetches all promotions that is stored in the database.
     *
     * @return a list of promotionDTO objects corresponding to all
     * the promotions that are stored in the database.
     */
    public List<PromotionDTO> getAllPromotions() {
        // Fetches all the promotions and store them in a list.
        List<Promotion> promotions = promotionRepository.findAll();
        // Goes through the list of promotions and converts each promotion object to promotionDTO
        // objects. Then it collect and return all the converted categories in a list.
        return promotions.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Fetches a specific promotionDTO based on the promotion ID.
     *
     * @param id The promotion ID of the desired promotion.
     * @return A promotionDTO that matches the specified promotion ID,
     *         or an empty optional if no promotion with the specified ID was found.
     */
    public Optional<PromotionDTO> getPromotionById(Long id) {
        Optional<Promotion> optionalPromotion = promotionRepository.findById(id);
        return optionalPromotion.map(this::convertToDTO);
    }

    /**
     * Converts a Promotion entity object into a PromotionDTO data transfer object.
     * Copies the properties from the Promotion entity to the PromotionDTO.
     * Also sets the productId manually for the PromotionDTO.
     *
     * @param promotion The Promotion entity object to convert.
     * @return The converted PromotionDTO object.
     */
    private PromotionDTO convertToDTO(Promotion promotion) {
        // Create new PromotionDTO object to store the converted promotion object
        PromotionDTO promotionDTO = new PromotionDTO();
        // Using BeanUtils library for copying the values in the promotion to the promotionDTO.
        BeanUtils.copyProperties(promotion, promotionDTO);
        // Set fields manually for the promotionDTO.
        promotionDTO.setProductID(promotion.getProduct().getProductId());
        return promotionDTO;
    }

    /**
     * Converts a PromotionDTO data transfer object into a Promotion entity object.
     * Copies the properties from the PromotionDTO to the Promotion entity.
     *
     * @param promotionDTO The PromotionDTO object to convert.
     * @return The converted Promotion entity object.
     */
    private Promotion convertToEntity(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();
        BeanUtils.copyProperties(promotionDTO, promotion);
        Product product = new Product();
        product.setProductId(promotionDTO.getProductID());
        promotion.setProduct(product);
        promotion.getProduct().setProductId(promotionDTO.getProductID());
        return promotion;
    }

    /**
     * This method calculates the price discount for a promotion
     *
     * @param product   the product
     * @param promotion the promotion
     * @return the float
     */
    public float calculateDiscountPrice (Product product, Promotion promotion) {
        float originalPrice = product.getPrice();
        float discountValue = promotion.getDiscountValue();

        if (promotion.getDiscountType().equals("percentage")) {
            return originalPrice * (1-discountValue/100);
        } else if (Objects.equals(promotion.getDiscountType(), "fixed")) {
            return originalPrice - discountValue;
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
    public void startPromotionAndUpdatePrice (Product productID, String description,
                                              String discountType, int discountValue,
                                              Date startDate, Date endDate) {
        Promotion promotion = new Promotion(productID, description, discountType,
            discountValue, startDate, endDate);
        promotionRepository.save(promotion);
        float discountPrice = calculateDiscountPrice(productID,promotion);
        productID.setPrice(discountPrice);
        productRepository.save(productID);
    }

}
