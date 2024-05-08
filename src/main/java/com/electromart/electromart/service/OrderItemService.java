package com.electromart.electromart.service;

import com.electromart.electromart.dto.OrderItemDTO;
import com.electromart.electromart.entity.OrderItem;
import com.electromart.electromart.entity.OrderItemId;
import com.electromart.electromart.entity.Product;
import com.electromart.electromart.repository.OrderItemRepository;
import com.electromart.electromart.repository.OrderRepository;
import com.electromart.electromart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing orderItems within the application.
 * It provides methods that is used by the OrderItemController for fetching and adding orderItems to the database.
 * It also handles the conversions between orderItem DTOs and entities.
 */
@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Fetches all categories that is stored in the database.
     * @return a list of categoryDTO objects corresponding to all the categories that are stored in the database.
     */
    public List<OrderItemDTO> getAllOrderItems() {
        // Fetches all the orderItems and store them in a list.
        List<OrderItem> orderItems = orderItemRepository.findAll();
        // Goes through the list of orderItems and converts each orderItem object to orderItemDTO objects.
        // Then it collect and return all the converted orderItems in a list.
        return orderItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new orderItem to the database by using a orderItemDTO object.
     * @param orderItemDTO The orderItemDTO object representing the orderItem to be added.
     * @return A orderItemDTO representation of the added orderItem.
     */
    public OrderItemDTO addOrderItem(OrderItemDTO orderItemDTO) {
        // Create new orderItem object based on the provided orderItemDTO.
        OrderItem orderItem = convertToEntity(orderItemDTO);
        // Save this orderItem in the database.
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        // Return the newly added orderItem as a orderItemDTO object.
        return convertToDTO(savedOrderItem);
    }

    /**
     * Fetches a specific orderItemDTO based on the orderItemID.
     * @param orderItemId The orderItemID of the desired orderItem.
     * @return A orderItemDTO that matches the specified orderItemID,
     * or an empty optional if no orderItem with the specified orderItemID was found.
     */
    public Optional<OrderItemDTO> getOrderItemById(OrderItemId orderItemId) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);
        return optionalOrderItem.map(this::convertToDTO);
    }

    /**
     * Deletes a orderItem object from the database based on a specified orderItemID.
     *
     * @param orderItemId The specified orderItemID of the orderItem to be deleted.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no orderItems with the specified ID are found.
     */
    /*
    public void deleteOrderItem(OrderItemId orderItemId) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
        // Check if any orderItem with the specified ID exist.
        if (orderItem.isPresent()) {
            orderItemRepository.delete(orderItem.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "OrderItem not found with the provided orderItem IDs");
        }
    }
     */

    /**
     * Converts orderItem object into a orderItem entity object.
     * @param orderItem The orderItem object to convert.
     * @return  The converted orderItem object.
     */
    public OrderItemDTO convertToDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setOrderId(orderItem.getId().getOrderID());
        orderItemDTO.setProductId(orderItem.getId().getProductID());
        orderItemDTO.setItemQuantity(orderItem.getItemQuantity());
        orderItemDTO.setItemSubtotal(orderItem.getItemSubtotal());
        return orderItemDTO;
    }

    /**
     * Converts orderItemDTO object into an orderItem entity object.
     * @param orderItemDTO The orderItem data transfer object to convert.
     * @return The converted orderItem object.
     */
    public OrderItem convertToEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        OrderItemId orderItemId = new OrderItemId();
        orderItemId.setOrderID(orderItemDTO.getOrderId());
        orderItemId.setProductID(orderItemDTO.getProductId());
        orderItem.setId(orderItemId);
        orderItem.setItemQuantity(orderItemDTO.getItemQuantity());
        orderItem.setItemSubtotal(orderItemDTO.getItemSubtotal());
        Optional<Product> optionalProduct = productRepository.findById(orderItemDTO.getProductId());
        optionalProduct.ifPresent(orderItem::setProduct);
        return orderItem;
    }
}
