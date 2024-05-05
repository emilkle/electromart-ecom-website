package com.electromart.electromart.service;

import com.electromart.electromart.dto.AddressDTO;
import com.electromart.electromart.entity.Address;
import com.electromart.electromart.entity.User;
import com.electromart.electromart.repository.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing addresses within the application.
 * It provides methods that is used by the AddressController for fetching and adding addresses to the database.
 * It also handles the conversions between address DTOs and entities.
 */
@Service
public class AddressService {

    //@Autowired
    //private AddressRepository addressRepository;

    private final AddressRepository addressRepository;
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Creates a new address to a corresponding user.
     * @param userID is returned from createUser method and used to create the corresponding address
     */
    public Address createAddress (User userID) {
        String address = "address from input";
        String postalCode = "postal code from input";
        String city = "city from input";
        String country = "country from input";

        return new Address(userID, address, postalCode, city, country);
    }

    /**
     * Fetches all addresses that is stored in the database.
     * @return a list of addressDTO objects corresponding to all the addresses that are stored in the database.
     */
    public List<AddressDTO> getAllAddresses() {
        // Fetches all the addresses and store them in a list.
        List<Address> addresses = addressRepository.findAll();
        // Goes through the list of addresses and converts each address object to addressDTO objects.
        // Then it collect and return all the converted addresses in a list.
        return addresses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new address to the database by using a addressDTO object.
     * @param addressDTO The addressDTO object representing the address to be added.
     * @return A addressDTO representation of the added address.
     */
    public AddressDTO addAddress(AddressDTO addressDTO) {
        // Create new address object based on the provided addressDTO.
        Address address = convertToEntity(addressDTO);
        // Save this address in the database.
        Address savedAddress = addressRepository.save(address);
        // Return the newly added address as a addressDTO object.
        return convertToDTO(savedAddress);
    }

    /**
     * Fetches a specific addressDTO based on the addressID.
     * @param id The addressID of the desired address.
     * @return A addressDTO that matches the specified addressID,
    or an empty optional if no address with the specified addressID was found.
     */
    public Optional<AddressDTO> getAddressById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.map(address -> convertToDTO(address));
    }

    /**
     * Deletes an address object from the database based on a specified addressID.
     * @param id The specified addressID of the address to be deleted.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no categories with the specified ID are found.
     */
    public void deleteAddress(Long id) {
        // Check if any address with the specified ID exist.
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found with ID: " + id);
        }
    }

    /**
     * Converts addressDTO object into an address entity object.
     * @param address The address object to convert.
     * @return  The converted address object.
     */
    private AddressDTO convertToDTO(Address address) {
        // Create new addressDTO object to store the converted address object
        AddressDTO addressDTO = new AddressDTO();
        // Using BeanUtils library for copying the values in the address to the addressDTO.
        BeanUtils.copyProperties(address, addressDTO);
        return addressDTO;
    }

    /**
     * Converts addressDTO object into an address entity object.
     * @param addressDTO The address data transfer object to convert.
     * @return The converted address object.
     */
    private Address convertToEntity(AddressDTO addressDTO) {
        // Create new address object to store the converted addressDTO object
        Address address = new Address();
        // Using BeanUtils library for copying the values in the addressDTO to the address.
        BeanUtils.copyProperties(addressDTO, address);
        return address;
    }
}
