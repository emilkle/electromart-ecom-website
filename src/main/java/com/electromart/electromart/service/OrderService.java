package com.electromart.electromart.service;

import com.electromart.electromart.dto.OrderDTO;
import com.electromart.electromart.entity.Order;
import com.electromart.electromart.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing orders within the application.
 * It provides methods that is used by the OrderController for fetching and adding orders to the database.
 * It also handles the conversions between order DTOs and entities.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Fetches all orders that is stored in the database.
     * @return a list of orderDTO objects corresponding to all the orders that are stored in the database.
     */
    public List<OrderDTO> getAllOrders() {
        // Fetches all the orders and store them in a list.
        List<Order> orders = orderRepository.findAll();
        // Goes through the list of orders and converts each order object to orderDTO objects.
        // Then it collect and return all the converted orders in a list.
        return orders.stream()
                .map(order -> convertToDTO(order))
                .collect(Collectors.toList());
    }

    /**
     * Adds a new order to the database by using a orderDTO object.
     * @param orderDTO The orderDTO object representing the order to be added.
     * @return A orderDTO representation of the added order.
     */
    public OrderDTO addOrder(OrderDTO orderDTO) {
        // Create new order object based on the provided orderDTO.
        Order order = convertToEntity(orderDTO);
        // Save this order in the database.
        Order savedOrder = orderRepository.save(order);
        // Return the newly added category as a categoryDTO object.
        return convertToDTO(order);
    }

    /**
     * Fetches a specific orderDTO based on the orderID.
     * @param id The orderID of the desired order.
     * @return A orderDTO that matches the specified orderID,
    or an empty optional if no order with the specified orderID was found.
     */
    public Optional<OrderDTO> getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.map(order -> convertToDTO(order));
    }

    /**
     * Deletes a order object from the database based on a specified orderID.
     * @param id The specified orderID of the order to be deleted.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no orders with the specified ID are found.
     */
    public void deleteOrder(Long id) {
        // Check if any order with the specified ID exist.
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with ID: " + id);
        }
    }

    /**
     * Converts orderDTO object into an order entity object.
     * @param order The order object to convert.
     * @return  The converted order object.
     */
    private OrderDTO convertToDTO(Order order) {
        // Create new orderDTO object to store the converted order object
        OrderDTO orderDTO = new OrderDTO();
        // Using BeanUtils library for copying the values in the order to the orderDTO.
        BeanUtils.copyProperties(order, orderDTO);
        return orderDTO;
    }

    /**
     * Converts orderDTO object into an order entity object.
     * @param orderDTO The order data transfer object to convert.
     * @return The converted order object.
     */
    private Order convertToEntity(OrderDTO orderDTO) {
        // Create new order object to store the converted orderDTO object
        Order order = new Order();
        // Using BeanUtils library for copying the values in the orderDTO to the order.
        BeanUtils.copyProperties(orderDTO, order);
        return order;
    }
}
