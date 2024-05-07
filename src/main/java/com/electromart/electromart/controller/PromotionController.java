package com.electromart.electromart.controller;

import com.electromart.electromart.dto.PromotionDTO;
import com.electromart.electromart.service.PromotionService;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
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
        // Fetch all promotions from the service.
        List<PromotionDTO> promotions = promotionService.getAllPromotions();
        // Check if any promotions are found.
        if (promotions.isEmpty()) {
            // If no promotions are found, throw a NOT_FOUND exception.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No promotions found.");
        } else {
            // If promotions are found, return the list of promotions.
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
        // Retrieve the promotion by its ID from the service.
        Optional<PromotionDTO> optionalPromotion = promotionService.getPromotionById(id);
        // Check if the promotion with the specified ID exists.
        if (optionalPromotion.isPresent()) {
            // If the promotion is found, return it with OK status.
            return new ResponseEntity<>(optionalPromotion.get(), HttpStatus.OK);
        } else {
            // If the promotion is not found, return NOT_FOUND status with a message.
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Promotion with ID: '" + id + "' not found.");
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
        // Add the new promotion to the database via the service.
        promotionService.addPromotion(promotionRequest);
        // Return a success message with CREATED status.
        return new ResponseEntity<>(
            "The requested promotion was created successfully.", HttpStatus.CREATED);
    }

    /**
     * Delete promotion response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/promotion_id={id}")
    public ResponseEntity<String> deletePromotion(@PathVariable String id) {
        try {
            // Parse the ID string to Long.
            Long promotionId = Long.parseLong(id);
            // Delete the promotion by ID from the service.
            promotionService.deletePromotion(promotionId);
            // Return success message with OK status.
            return ResponseEntity.ok().body("Promotion with ID: '"
                + promotionId + "' successfully deleted.");
        } catch (ResponseStatusException e) {
            // If promotion not found, return NOT_FOUND status with error message.
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Promotion with ID: '" + id + "' was not found.");
        } catch (NumberFormatException e) {
            // If ID is invalid, return BAD_REQUEST status with error message.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid promotion ID. Please use an integer number as ID.");
        }
    }

    /**
     * Handle http message not readable response entity.
     *
     * @param e the error
     * @return the response entity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Invalid JSON payload. Please ensure that the all the data types are correct.");
    }
}
