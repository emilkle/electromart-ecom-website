package com.electromart.electromart.controller;

import com.electromart.electromart.dto.AddressDTO;
import com.electromart.electromart.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

/**
 * RestController for managing addresses in the database.
 * The controller utilize methods from the AddressService to interact with the database.
 * It provides the application with endpoints used for HTTP GET, POST and DELETE requests,
 with corresponding endpoints for each request method.
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("")
    public List<AddressDTO> fetchAllAddress() {
        List<AddressDTO> addresses = addressService.getAllAddresses();
        if (addresses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " No addresses found");
        } else {
            return addresses;
        }
    }

    /**
     * Handles POST requests for adding a new address to the database.
     * @param addressDTO The addressDTO representing the address to be added.
     * @return ResponseEntity containing the added addressDTO and a http status code CREATED.
     */
    @PostMapping("")
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        // Add the address to the database
        AddressDTO savedAddress = addressService.addAddress(addressDTO);
        // Return a response with the saved address and HTTP status code
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    /**
     * Handles GET request for fetching a specific address by its ID, from the database.
     * If address is not found, the method returns a message informing about this.
     * @param id The addressID of the desired address.
     * @return ResponseEntity containing the addressDTO if found and HTTP status OK, otherwise NOT_FOUND.
     */
    @GetMapping("/{id}")
    // Return type set to "?" for flexible return type in case the desired address does not exist and
    // an address not found message needs to be returned.
    public ResponseEntity<?> getAddressById(@PathVariable Long id) {
        // Get the address
        Optional<AddressDTO> optionalAddress = addressService.getAddressById(id);
        // Check if the specified address exists
        if (optionalAddress.isPresent()) {
            //Return the address and HTTP status code OK
            return new ResponseEntity<>(optionalAddress.get(), HttpStatus.OK);
        } else {
            // If the address does not exist, return HTTP status code NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address with ID: '" + id + "' not found");
        }
    }

    /**
     * Deletes an address based on a specified addressID, and provides a feedback on the outcome.
     * @param id The addressID of the address to be deleted.
     * @return ResponseEntity containing the outcome of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        try {
            addressService.deleteAddress(id);
            return ResponseEntity.ok().body("Address with ID: '" + id + "' successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found with ID: " + id);
        }
    }
}
