package com.electromart.electromart.service;

import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.repository.BrandRepository;
import java.util.Optional;
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
}


