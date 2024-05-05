package com.electromart.electromart.service;

import com.electromart.electromart.controller.ProductController;
import com.electromart.electromart.dto.InventoryDTO;
import com.electromart.electromart.entity.Inventory;
import com.electromart.electromart.repository.InventoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductController product;

    public InventoryService(InventoryRepository inventoryRepository, ProductController product) {
        this.inventoryRepository = inventoryRepository;
        this.product = product;
    }

    /**
     * Method for updating inventory quantity of a product after a purchase.
     */
    public void updateInventoryAfterPurchase (long productID, int purchaseQuantity) throws Exception {
        Inventory product = inventoryRepository.findById(productID)
                .orElseThrow(()-> new Exception("Product not found"));

        int currentStock = product.getQuantity();
        int updatedStock = currentStock - purchaseQuantity;

        if (updatedStock < 0) {
            throw new Exception("Product has not enough stock items. Refill inventory.");
        }
        product.setQuantity(updatedStock);
        inventoryRepository.save(product);
    }

    /**
     *
     * @param productID externally supplied productID for checking inventory amount
     * @param shoppingCartAmount externally supplied amount to check against inventoryAmount
     * @throws Exception productID not found, shoppingCartAmount negative or zero.
     */
    public boolean checkInventoryQuantity(long productID, int shoppingCartAmount) throws Exception {

        if (shoppingCartAmount <= 0) {
            throw new Exception("Invalid number supplied.");
        }
        Inventory product = inventoryRepository.findById(productID)
                .orElseThrow(()-> new Exception("Product not found"));

        int productStockQuantity = product.getQuantity();

        return (shoppingCartAmount<=productStockQuantity);

    }

    //########################NEWLY ADDED/REFACTORED METHODS########################################

    /**
     * Updates the quantity of a product in the inventory, when a product is purchased.
     * @param productId The ID of the product that is being purchased.
     * @param purchaseQuantity The specified quantity that is being purchased.
     * @throws Exception if the product is not found in the database, or if stock quantity is insufficient.
     */
    public void updateInventoryAfterPurchaseUsingDTO(Long productId, int purchaseQuantity) throws Exception {
        Inventory product = inventoryRepository.findById(productId)
                .orElseThrow(() -> new Exception("No product with ID: " + productId + " was found in the inventory."));

        int updatedStock = product.getQuantity() - purchaseQuantity;

        if (updatedStock < 0) {
            throw new Exception("Stock quantity: " + product.getQuantity()
                    + " is less than purchase quantity: " + purchaseQuantity
                    + " Inventory needs to be refilled.");
        }
        product.setQuantity(updatedStock);
        inventoryRepository.save(product);
    }

    /**
     * Checks if the specified purchase quantity is valid, and if a specified product exists in the inventory.
     * @param productId The inventoryDTO object representing the inventory item to check the stock for.
     * @param shoppingCartAmount The number of items of a specific product in the shopping cart.
     * @return true if product exists in the inventory and shopping cart amount is valid (i.e. not 0 or negative).
     * @throws Exception if product is not found in the database, and if an invalid shopping cart amount is provided.
     */
    public boolean checkInventoryQuantity(Long productId, int shoppingCartAmount) throws Exception {
        if (shoppingCartAmount <= 0) {
            throw new Exception("Invalid number provided: " + shoppingCartAmount);
        }
        Inventory product = inventoryRepository.findById(productId)
                .orElseThrow(()-> new Exception("No product with ID: " + productId + " was found in the inventory."));

        return (shoppingCartAmount <= product.getQuantity());
    }

    /**
     * Fetches all inventory items that is stored in the database.
     * @return a list of inventoryDTO objects corresponding to all the inventory items that are stored in the database.
     */
    public List<InventoryDTO> getAllInventoryItems() {
        // Fetches all the inventory items and store them in a list.
        List<Inventory> inventoryItems = inventoryRepository.findAll();
        // Goes through the list of inventory items and converts each item object to InventoryDTO objects.
        // Then it collect and return all the converted inventory items in a list.
        return inventoryItems.stream()
                .map(inventory -> convertToDTO(inventory))
                .collect(Collectors.toList());
    }

    /**
     * Adds a new inventory item to the database by using a inventoryDTO object.
     * @param inventoryDTO The inventoryDTO object representing the inventory item to be added.
     * @return A inventoryDTO representation of the added item.
     */
    public InventoryDTO addInventoryItem(InventoryDTO inventoryDTO) {
        // Create new inventory object based on the provided inventoryDTO.
        Inventory inventory = convertToEntity(inventoryDTO);
        // Save this inventory item in the database.
        Inventory savedInventoryItem = inventoryRepository.save(inventory);
        // Return the newly added inventory item as a inventoryDTO object.
        return convertToDTO(savedInventoryItem);
    }

    /**
     * Fetches a specific inventoryDTO based on the inventoryID.
     * @param id The inventoryID of the desired inventory item.
     * @return A inventoryDTO that matches the specified inventoryID,
    or an empty optional if no inventory item with the specified inventoryID was found.
     */
    public Optional<InventoryDTO> getInventoryItemById(Long id) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        return optionalInventory.map(inventoryItem -> convertToDTO(inventoryItem));
    }

    /**
     * Deletes a inventory object from the database based on a specified inventoryID.
     * @param id The specified inventoryID of the inventory item to be deleted.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no categories with the specified ID are found.
     */
    public void deleteInventoryItem(Long id) {
        // Check if any inventory item with the specified ID exist.
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory item not found with ID: " + id);
        }
    }

    /**
     * Converts inventoryDTO object into an inventory entity object.
     * @param inventory The inventory object to convert.
     * @return  The converted inventory object.
     */
    private InventoryDTO convertToDTO(Inventory inventory) {
        // Create new inventoryDTO object to store the converted inventory object
        InventoryDTO inventoryDTO = new InventoryDTO();
        // Using BeanUtils library for copying the values in the inventory to the categoryDTO.
        BeanUtils.copyProperties(inventory, inventoryDTO);
        //Set inventoryID and productID manually for the inventoryDTO
        inventoryDTO.setInventoryID(inventory.getId());
        inventoryDTO.setProduct(inventory.getProduct().getProductId());
        return inventoryDTO;
    }

    /**
     * Converts inventoryDTO object into an inventory entity object.
     * @param inventoryDTO The inventory data transfer object to convert.
     * @return The converted inventory object.
     */
    private Inventory convertToEntity(InventoryDTO inventoryDTO) {
        // Create new inventory object to store the converted inventoryDTO object
        Inventory inventory = new Inventory();
        // Using BeanUtils library for copying the values in the categoryDTO to the category.
        BeanUtils.copyProperties(inventoryDTO, inventory);
        return inventory;
    }

}
