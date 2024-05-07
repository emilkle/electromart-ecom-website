package com.electromart.electromart.controller;

import com.electromart.electromart.dto.ShippingDTO;
import com.electromart.electromart.service.ShippingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling shipping-related HTTP requests.
 */
@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    /**
     * Constructor for ShippingController.
     *
     * @param shippingService The service responsible for handling shipping-related operations.
     */
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    /**
     * Handles GET requests to retrieve all shipping options.
     *
     * @return A list of ShippingDTO objects representing all shipping options.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no shipping options are found.
     */
    @GetMapping({"", "/"})
    public List<ShippingDTO> fetchAllShipping() {
        List<ShippingDTO> shippingList = shippingService.getAllShipping();
        if (shippingList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No shipping options found.");
        } else {
            return shippingList;
        }
    }

    /**
     * Handles GET requests to retrieve a specific shipping option by ID.
     *
     * @param id The ID of the shipping option to retrieve.
     * @return ResponseEntity with the ShippingDTO object if found, or NOT_FOUND status with
     * a message if not found.
     */
    @GetMapping("/shipping_id={id}")
    public ResponseEntity<?> getShippingById(@PathVariable Long id) {
        Optional<ShippingDTO> optionalShipping = shippingService.getShippingById(id);
        if (optionalShipping.isPresent()) {
            return new ResponseEntity<>(optionalShipping.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Shipping option with ID: '" + id + "' not found.");
        }
    }

    /**
     * Handles POST requests to add a new shipping option.
     *
     * @param shippingRequest The ShippingDTO object representing the new shipping option.
     * @return ResponseEntity with a success message and CREATED status.
     */
    @PostMapping({"", "/"})
    public ResponseEntity<String> addNewShipping(@RequestBody ShippingDTO shippingRequest) {
        shippingService.addShipping(shippingRequest);
        return new ResponseEntity<>(
            "The requested shipping option was created successfully.", HttpStatus.CREATED);
    }

    /**
     * Handles HTTP message not readable exceptions.
     *
     * @param e The HttpMessageNotReadableException object.
     * @return ResponseEntity with a BAD_REQUEST status and an error message.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Invalid JSON payload. Please ensure that all the data types are correct.");
    }
}