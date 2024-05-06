package com.electromart.electromart.service;

import com.electromart.electromart.dto.BrandDTO;
import com.electromart.electromart.dto.CategoryDTO;
import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.entity.Category;
import com.electromart.electromart.repository.BrandRepository;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Brand service.
 */
@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    /**
     * Gets all brands.
     *
     * @return the all brands
     */
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    /**
     * Get name from id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<String> getNameFromID(Long id){
        Optional<Brand> brandParam = getAllBrands()
            .stream()
            .filter(brand -> brand.getBrandId().equals(id))
            .findFirst();
        return brandParam.map(Brand::getName);
    }

    /**
     * Get description from name optional.
     *
     * @param name the name
     * @return the optional
     */
    public Optional<String> getDescriptionFromName(String name){
        Optional<Brand> brandParam = getAllBrands()
            .stream().filter(brand -> brand.getName().equals(name))
            .findFirst();
        return brandParam.map(Brand::getDescription);
    }

    /**
     * Converts brandDTO object into a brand entity object.
     * @param brand The brand object to convert.
     * @return  The converted brand object.
     */
    private BrandDTO convertToDTO(Brand brand) {
        // Create new brandDTO object to store the converted brand object
        BrandDTO brandDTO = new BrandDTO();
        // Using BeanUtils library for copying the values in the brand to the brandDTO.
        BeanUtils.copyProperties(brand, brandDTO);
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
     * @return A brandDTO representation of the added brand.
     */
    public BrandDTO addBrand(BrandDTO brandDTO) {
        // Create new brand object based on the provided brandDTO.
        Brand brand = convertToEntity(brandDTO);
        // Save this brand in the database.
        Brand savedBrand = brandRepository.save(brand);
        // Return the newly added brand as a brandDTO object.
        return convertToDTO(savedBrand);
    }
}


