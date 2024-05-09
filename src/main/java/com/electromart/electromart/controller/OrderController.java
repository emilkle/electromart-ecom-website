package com.electromart.electromart.controller;

import com.electromart.electromart.dto.OrderDTO;
import com.electromart.electromart.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

/**
 * RestController for managing orders in the database.
 * The controller utilize methods from the OrderService to interact with the database.
 * It provides the application with endpoints used for HTTP GET, POST and DELETE requests,
 with corresponding endpoints for each request method.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Handles GET requests for returning all the orders present in the database.
     * @return A list with all the orders in the database.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no orders are found.
     */
    @GetMapping({"","/"})
    public ResponseEntity<?> fetchAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        // If no orders exist return HTTP status code OK and message
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("The system does not contain any orders.");
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

    /**
     * Handles POST requests for adding a new order to the database.
     * @param orderDTO The orderDTO representing the order to be added.
     * @return ResponseEntity containing the added orderDTO and a http status code CREATED.
     */
    @PostMapping({"","/"})
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        // Add the order to the database
        OrderDTO savedOrder = orderService.addOrder(orderDTO);
        // Return a response with the saved order and HTTP status code
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    /**
     * Handles GET request for fetching a specific order by its ID, from the database.
     * If order is not found, the method returns a message informing about this.
     * @param id The orderID of the desired order.
     * @return ResponseEntity containing the orderDTO if found and HTTP status OK, otherwise NOT_FOUND.
     */
    @GetMapping("/order_id={id}")
    // Return type set to "?" for flexible return type in case the desired order does not exist and
    // a order not found message needs to be returned.
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        // Get the order
        Optional<OrderDTO> optionalOrder = orderService.getOrderById(id);
        // Check if the specified order exists
        if (optionalOrder.isPresent()) {
            //Return the order and HTTP status code OK
            return new ResponseEntity<>(optionalOrder.get(), HttpStatus.OK);
        } else {
            // If the order does not exist, return HTTP status code OK
            return ResponseEntity.status(HttpStatus.OK).body("Order with ID: '" + id + "' not found");
        }
    }

    /**
     * Deletes an order based on a specified orderID, and provides a feedback on the outcome.
     * @param id The orderID of the order to be deleted.
     * @return ResponseEntity containing the outcome of the deletion.
     */
    @DeleteMapping("/order_id={id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().body("Order with ID: '" + id + "' successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body("Order not found with ID: " + id);
        }
    }
}
