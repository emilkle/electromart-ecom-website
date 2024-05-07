package com.electromart.electromart.service;

import com.electromart.electromart.dto.ShippingDTO;
import com.electromart.electromart.entity.Order;
import com.electromart.electromart.entity.Shipping;
import com.electromart.electromart.repository.ShippingRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service class responsible for handling shipping-related operations.
 * Acts as an intermediary between the controller and the repository layer.
 */
@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;

    /**
     * Constructor for ShippingService.
     *
     * @param shippingRepository The repository for managing Shipping entities.
     */
    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    /**
     * Fetches all shipping options stored in the database.
     *
     * @return A list of ShippingDTO objects corresponding to all shipping options in the database.
     */
    public List<ShippingDTO> getAllShipping() {
        List<Shipping> shippingList = shippingRepository.findAll();
        return shippingList.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Adds a new shipping option to the database using a ShippingDTO object.
     *
     * @param shippingDTO The ShippingDTO object representing the shipping option to be added.
     */
    public void addShipping(ShippingDTO shippingDTO) {
        Shipping shipping = convertToEntity(shippingDTO);
        Shipping savedShipping = shippingRepository.save(shipping);
        convertToDTO(savedShipping);
    }

    /**
     * Fetches a specific shipping option based on the shipping ID.
     *
     * @param id The shipping ID of the desired shipping option.
     * @return An Optional containing a ShippingDTO that matches the specified ID, or an empty
     * Optional if no shipping option is found.
     */
    public Optional<ShippingDTO> getShippingById(Long id) {
        Optional<Shipping> optionalShipping = shippingRepository.findById(id);
        return optionalShipping.map(this::convertToDTO);
    }


    /**
     * Converts a Shipping entity object into a ShippingDTO data transfer object.
     *
     * @param shipping The Shipping entity object to convert.
     * @return The converted ShippingDTO object.
     */
    private ShippingDTO convertToDTO(Shipping shipping) {
        ShippingDTO shippingDTO = new ShippingDTO();
        BeanUtils.copyProperties(shipping, shippingDTO);
        shippingDTO.setOrderID(shipping.getOrder().getOrderID());
        return shippingDTO;
    }

    /**
     * Converts a ShippingDTO data transfer object into a Shipping entity object.
     *
     * @param shippingDTO The ShippingDTO object to convert.
     * @return The converted Shipping entity object.
     */
    private Shipping convertToEntity(ShippingDTO shippingDTO) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(shippingDTO, shipping);
        Order order = new Order();
        order.setOrderID(shippingDTO.getShippingID());
        shipping.setOrder(order);
        shipping.getOrder().setOrderID(shippingDTO.getOrderID());
        return shipping;
    }
}
