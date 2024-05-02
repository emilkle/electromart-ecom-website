package com.electromart.electromart.service;

import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.repository.BrandRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<String> getNameByID(Long id){
        Optional<Brand> brandParam = getAllBrands()
            .stream()
            .filter(brand -> brand.getBrandId().equals(id))
            .findFirst();
        return brandParam.map(Brand::getName);
    }
}


