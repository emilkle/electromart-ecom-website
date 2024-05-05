package com.electromart.electromart.service;

import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.entity.Product;
import com.electromart.electromart.repository.ProductRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Product service.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Gets all products.
     *
     * @return the all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Find product from brand id list.
     *
     * @param id the id
     * @return the list
     */
    public List<Product> findProductFromBrandId(Long id) {
        return getAllProducts()
            .stream()
            .filter(product -> product.getBrand().getBrandId().equals(id))
            .toList();
    }
}
