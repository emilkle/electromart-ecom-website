package com.electromart.electromart.controller;

import com.electromart.electromart.dto.InventoryDTO;
import com.electromart.electromart.service.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

/**
 * RestController for managing the inventory in the database.
 * The controller utilize methods from the InventoryService to interact with the database.
 * It provides the application with endpoints used for HTTP GET, POST and DELETE requests,
 with corresponding endpoints for each request method.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Handles GET requests for returning all the inventory items present in the database.
     * @return A list with all the inventory items in the database.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no inventory items are found.
     */
    @GetMapping("")
    public ResponseEntity<?> fetchAllInventoryItems() {
        List<InventoryDTO> inventoryItems = inventoryService.getAllInventoryItems();
        if (inventoryItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("The system does not contain any inventory items.");
        } else {
            return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
        }
    }

    /**
     * Handles POST requests for adding a new inventory item to the database.
     * @param inventoryDTO The inventoryDTO representing the inventory item to be added.
     * @return ResponseEntity containing the added inventoryDTO and a http status code CREATED.
     */
    @PostMapping("")
    public ResponseEntity<InventoryDTO> addInventoryItem(@RequestBody InventoryDTO inventoryDTO) {
        // Add the inventory item to the database
        InventoryDTO savedInventoryItem = inventoryService.addInventoryItem(inventoryDTO);
        // Return a response with the saved inventory item and HTTP status code
        return new ResponseEntity<>(savedInventoryItem, HttpStatus.CREATED);
    }

    /**
     * Handles GET request for fetching a specific inventory item by its ID, from the database.
     * If item is not found, the method returns a message informing about this.
     * @param id The inventoryID of the desired inventory item.
     * @return ResponseEntity containing the inventoryDTO if found and HTTP status OK, otherwise NOT_FOUND.
     */
    @GetMapping("/{id}")
    // Return type set to "?" for flexible return type in case the desired inventiory item does not exist and
    // an inventory item not found message needs to be returned.
    public ResponseEntity<?> getInventoryItemById(@PathVariable Long id) {
        // Get the inventory item
        Optional<InventoryDTO> optionalInventory = inventoryService.getInventoryItemById(id);
        // Check if the specified inventory item exists
        if (optionalInventory.isPresent()) {
            //Return the inventory item and HTTP status code OK
            return new ResponseEntity<>(optionalInventory.get(), HttpStatus.OK);
        } else {
            // If the inventory item does not exist, return HTTP status code NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory item with ID: '" + id + "' not found");
        }
    }

    /**
     * Deletes an inventory item based on a specified inventoryID, and provides a feedback on the outcome.
     * @param id The inventoryID of the inventory item to be deleted.
     * @return ResponseEntity containing the outcome of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventoryItem(@PathVariable Long id) {
        try {
            inventoryService.deleteInventoryItem(id);
            return ResponseEntity.ok().body("Inventory item with ID: '" + id + "' successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory item not found with ID: " + id);
        }
    }
}
