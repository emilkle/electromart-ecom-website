package com.electromart.electromart.service;

import com.electromart.electromart.dto.BrandDTO;
import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.repository.BrandRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Brand service.
 */
@Service
public class BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    /**
     * Fetches all brands that is stored in the database.
     *
     * @return a list of brandDTO objects corresponding to all the brands
     * that are stored in the database.
     */
    public List<BrandDTO> getAllBrands() {
        // Fetches all the brands and store them in a list.
        List<Brand> brands = brandRepository.findAll();
        // Goes through the list of brands and converts each brand object to brandDTO objects.
        // Then it collect and return all the converted brands in a list.
        return brands.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Get name from id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<String> getNameFromID(Long id){
        Optional<BrandDTO> brandDTO = getAllBrands()
            .stream()
            .filter(brand -> brand.getBrandId().equals(id))
            .findFirst();
        return brandDTO.map(BrandDTO::getName);
    }

    /**
     * Get description from name optional.
     *
     * @param name the name
     * @return the optional
     */
    public Optional<String> getDescriptionFromName(String name){
        Optional<BrandDTO> brandDTO = getAllBrands()
            .stream()
            .filter(brand -> brand.getName().equals(name))
            .findFirst();
        return brandDTO.map(BrandDTO::getDescription);
    }

    /**
     * Converts brandDTO object into a brand entity object.
     * @param brand The brand object to convert.
     * @return  The converted brand object.
     */
    private BrandDTO convertToDTO(Brand brand) {
        // Create new brandBTO object to store the converted brand object
        BrandDTO brandDTO = new BrandDTO();
        // Using BeanUtils library for copying the values in the brand to the brandDTO.
        BeanUtils.copyProperties(brand, brandDTO);
        //Set brandID manually for the brandDTO.
        brandDTO.setBrandId(brand.getBrandId());
        return brandDTO;
    }

    /**
     * Converts brandDTO object into a brand entity object.
     * @param brandDTO The brand data transfer object to convert.
     * @return The converted brand object.
     */
    private Brand convertToEntity(BrandDTO brandDTO) {
        // Create new brand object to store the converted brandDTO object
        Brand brand = new Brand();
        // Using BeanUtils library for copying the values in the brandDTO to the brand.
        BeanUtils.copyProperties(brandDTO, brand);
        return brand;
    }

    /**
     * Adds a new brand to the database by using a brandDTO object.
     *
     * @param brandDTO The brandDTO object representing the brand to be added.
     */
    public void addBrand(BrandDTO brandDTO) {
        // Create new brand object based on the provided brandDTO.
        Brand brand = convertToEntity(brandDTO);
        // Save this brand in the database.
        Brand savedBrand = brandRepository.save(brand);
        // Return the newly added brand as a brandDTO object.
        convertToDTO(savedBrand);
    }
}


