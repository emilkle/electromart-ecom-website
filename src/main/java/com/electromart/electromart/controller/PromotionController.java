package com.electromart.electromart.controller;

import com.electromart.electromart.dto.BrandDTO;
import com.electromart.electromart.dto.CategoryDTO;
import com.electromart.electromart.dto.PromotionDTO;
import com.electromart.electromart.entity.Promotion;
import com.electromart.electromart.service.CategoryService;
import com.electromart.electromart.service.ProductReviewService;
import com.electromart.electromart.service.PromotionService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    /**
     * Handles GET requests for returning all the promotions present in the database.
     * @return A list with all the promotions in the database.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no promotions are found.
     */
    @GetMapping({"", "/"})
    public List<PromotionDTO> fetchAllPromotions() {
        List<PromotionDTO> promotions = promotionService.getAllPromotions();
        if (promotions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No promotions found");
        } else {
            return promotions;
        }
    }

    /**
     * Gets promotion by id.
     *
     * @param id the id
     * @return the promotion by id
     */
    @GetMapping("/promotion_id={id}")
    public ResponseEntity<?> getPromotionById(@PathVariable Long id) {
        Optional<PromotionDTO> optionalPromotion = promotionService.getPromotionById(id);
        if (optionalPromotion.isPresent()) {
            return new ResponseEntity<>(optionalPromotion.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Promotion with ID: '" + id + "' not found");
        }
    }

    /**
     * Add new promotion response entity.
     *
     * @param promotionRequest the brand request
     * @return the response entity
     */
    @PostMapping({"", "/"})
    public ResponseEntity<String> addNewPromotion(@RequestBody PromotionDTO promotionRequest) {
        // Add the promotion to the database
        promotionService.addPromotion(promotionRequest);
        // Return a response with the saved promotion and HTTP status code
        return new ResponseEntity<>("The requested promotion was " +
            "created successfully.", HttpStatus.CREATED);
    }
}
