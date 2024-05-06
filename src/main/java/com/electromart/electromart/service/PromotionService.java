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

/**
 * Service class responsible for handling promotion-related operations.
 * Acts as an intermediary between the controller and the repository layer.
 */
@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    /**
     * Constructor for PromotionService.
     *
     * @param promotionRepository The repository for managing Promotion entities.
     * @param productRepository   The repository for managing Product entities.
     */
    public PromotionService(PromotionRepository promotionRepository,
                            ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.productRepository = productRepository;
    }

    /**
     * Fetches all promotions that are stored in the database.
     *
     * @return A list of promotionDTO objects corresponding to all promotions in the database.
     */
    public List<PromotionDTO> getAllPromotions() {
        // Fetch all promotions and convert them to DTOs.
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Adds a new promotion to the database using a promotionDTO object.
     *
     * @param promotionDTO The promotionDTO object representing the promotion to be added.
     */
    public void addPromotion(PromotionDTO promotionDTO) {
        // Create a new promotion entity based on the provided promotionDTO.
        Promotion promotion = convertToEntity(promotionDTO);
        // Save the promotion in the database.
        Promotion savedPromotion = promotionRepository.save(promotion);
        // Convert the saved promotion to a DTO.
        convertToDTO(savedPromotion);
    }

    /**
     * Fetches a specific promotionDTO based on the promotion ID.
     *
     * @param id The promotion ID of the desired promotion.
     * @return An Optional containing a promotionDTO that matches the specified ID, or an empty Optional if no promotion is found.
     */
    public Optional<PromotionDTO> getPromotionById(Long id) {
        Optional<Promotion> optionalPromotion = promotionRepository.findById(id);
        return optionalPromotion.map(this::convertToDTO);
    }

    /**
     * Deletes a promotion from the database based on a specified promotion ID.
     *
     * @param id The promotion ID of the promotion to be deleted.
     * @throws ResponseStatusException If no promotion with the specified ID is found.
     */
    public void deletePromotion(Long id) {
        if (promotionRepository.existsById(id)) {
            promotionRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No promotion found with ID: " + id);
        }
    }

    /**
     * Converts a Promotion entity object into a PromotionDTO data transfer object.
     *
     * @param promotion The Promotion entity object to convert.
     * @return The converted PromotionDTO object.
     */
    private PromotionDTO convertToDTO(Promotion promotion) {
        // Convert Promotion entity to DTO and set product ID.
        PromotionDTO promotionDTO = new PromotionDTO();
        BeanUtils.copyProperties(promotion, promotionDTO);
        promotionDTO.setProductID(promotion.getProduct().getProductId());
        return promotionDTO;
    }

    /**
     * Converts a PromotionDTO data transfer object into a Promotion entity object.
     *
     * @param promotionDTO The PromotionDTO object to convert.
     * @return The converted Promotion entity object.
     */
    private Promotion convertToEntity(PromotionDTO promotionDTO) {
        // Convert PromotionDTO to entity and set product.
        Promotion promotion = new Promotion();
        BeanUtils.copyProperties(promotionDTO, promotion);
        Product product = new Product();
        product.setProductId(promotionDTO.getProductID());
        promotion.setProduct(product);
        promotion.getProduct().setProductId(promotionDTO.getProductID());
        return promotion;
    }

    /**
     * Calculates the price discount for a promotion.
     *
     * @param product   The product.
     * @param promotion The promotion.
     * @return The discounted price.
     */
    public float calculateDiscountPrice(Product product, Promotion promotion) {
        float originalPrice = product.getPrice();
        float discountValue = promotion.getDiscountValue();
        if (promotion.getDiscountType().equals("percentage")) {
            return originalPrice * (1 - discountValue / 100);
        } else if (Objects.equals(promotion.getDiscountType(), "fixed")) {
            return originalPrice - discountValue;
        } else {
            throw new IllegalArgumentException("Invalid discount type");
        }
    }

    /**
     * Starts a promotion and updates the corresponding product with the correct discounted price.
     *
     * @param productID     The product's ID.
     * @param description   The description of the promotion.
     * @param discountType  The type of discount (percentage or fixed).
     * @param discountValue The amount of discount.
     * @param startDate     The start date of the promotion.
     * @param endDate       The end date of the promotion.
     */
    public void startPromotionAndUpdatePrice(Product productID, String description,
                                             String discountType, int discountValue,
                                             Date startDate, Date endDate) {
        Promotion promotion = new Promotion(productID, description, discountType,
            discountValue, startDate, endDate);
        promotionRepository.save(promotion);
        float discountPrice = calculateDiscountPrice(productID, promotion);
        productID.setPrice(discountPrice);
        productRepository.save(productID);
    }
}
