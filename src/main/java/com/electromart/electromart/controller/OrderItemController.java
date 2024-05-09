package com.electromart.electromart.controller;

import com.electromart.electromart.dto.OrderItemDTO;
import com.electromart.electromart.entity.OrderItemId;
import com.electromart.electromart.entity.Product;
import com.electromart.electromart.repository.OrderRepository;
import com.electromart.electromart.repository.ProductRepository;
import com.electromart.electromart.service.OrderItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderItemController(OrderItemService orderItemService, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderItemService = orderItemService;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }


    /**
     * Handles GET requests for returning all the orderItems present in the database.
     * @return A list with all the orderItems in the database.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no orderItems are found.
     */
    @GetMapping({"","/"})
    public ResponseEntity<?> fetchAllCategories() {
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItems();
        if (orderItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("The system does not contain any orderItems.");
        } else {
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        }
    }

    /**
     * Handles POST requests for adding a new orderItem to the database.
     * @param orderItemDTO The orderItemDTO representing the orderItem to be added.
     * @return ResponseEntity containing the added orderItemDTO and a http status code CREATED.
     */
    @PostMapping({"","/"})
    public ResponseEntity<?> addOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        boolean errorFlag = false;
        ResponseEntity<?> response = null;
        if (orderItemDTO.getItemQuantity() > 0) {
            if (!productRepository.existsById(orderItemDTO.getProductId())
                    && !orderRepository.existsById(orderItemDTO.getOrderId())) {
                errorFlag = true;
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not add orderItem, "
                        + "because neither product ID: " + orderItemDTO.getProductId()
                        + " or order ID: " + orderItemDTO.getOrderId() + " exist in the database.");
            }
            if (!productRepository.existsById(orderItemDTO.getProductId())) {
                errorFlag = true;
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not add orderItem, "
                        + "because the product ID: " + orderItemDTO.getProductId() + " does not exist in the database.");
            }
            if (!orderRepository.existsById(orderItemDTO.getOrderId())) {
                errorFlag = true;
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not add orderItem, "
                        + "because the product ID: " + orderItemDTO.getOrderId() + " does not exist in the database.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not add orderItem, "
            + "because the provided quantity: " + orderItemDTO.getItemQuantity() + " is not valid.");
        }

        if (errorFlag) {
            return response;
        } else {
            int qty = orderItemDTO.getItemQuantity();
            Optional<Product> optionalProduct = productRepository.findById(orderItemDTO.getProductId());
            float price = optionalProduct.get().getPrice();
            orderItemDTO.setItemSubtotal(qty * price);
            // Add the orderItem to the database
            OrderItemDTO savedOrderItem = orderItemService.addOrderItem(orderItemDTO);
            // Return a response with the saved orderItem and HTTP status code
            return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
        }
    }

    /**
     * Handles GET request for fetching a specific orderItem by its ID, from the database.
     * If orderItem is not found, the method returns a message informing about this.
     * @param orderId A PPK in the orderItemID of the desired orderItem.
     * @param productId A PPK in the orderItemID of the desired orderItem.
     * @return ResponseEntity containing the orderItemDTO if found and HTTP status OK, otherwise NOT_FOUND.
     */
    @GetMapping("/orderitem_id={orderId},{productId}")
    // Return type set to "?" for flexible return type in case the desired orderItem does not exist and
    // a orderItem not found message needs to be returned.
    public ResponseEntity<?> getOrderItemById(@PathVariable Long orderId, @PathVariable Long productId) {
        OrderItemId orderItemId = new OrderItemId(orderId, productId);
        // Get the category
        Optional<OrderItemDTO> optionalOrderItem = orderItemService.getOrderItemById(orderItemId);
        // Check if the specified orderItem exists
        if (optionalOrderItem.isPresent()) {
            //Return the orderItem and HTTP status code OK
            return new ResponseEntity<>(optionalOrderItem.get(), HttpStatus.OK);
        } else {
            // If the orderItem does not exist, return HTTP status code NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The provided orderId: " + orderId
                    + " and productId: " + productId + " did not return any orderItems from the database.");
        }
    }

    /**
     * Deletes a orderItem based on a specified orderItemID, and provides a feedback on the outcome.
     * @param orderId A PPK in orderItemID of the orderItem to be deleted.
     * @param productId A PPK in orderItemID of the orderItem to be deleted.
     * @return ResponseEntity containing the outcome of the deletion.
     */
    /*
    @DeleteMapping("/{orderId},{productId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long orderId,@PathVariable Long productId) {
        OrderItemId orderItemId = new OrderItemId(orderId, productId);
        try {
            orderItemService.deleteOrderItem(orderItemId);
            return ResponseEntity.ok().body("OrderItem with order ID: '" + orderId + "', and product ID: '"
                    + productId + "' was successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OrderItem not found with the provided IDs");
        }
    }
     */
}
