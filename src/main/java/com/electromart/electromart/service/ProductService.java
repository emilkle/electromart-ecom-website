package com.electromart.electromart.service;

import com.electromart.electromart.dto.ProductDTO;
import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.entity.Category;
import com.electromart.electromart.entity.Product;
import com.electromart.electromart.repository.ProductRepository;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing products within the application.
 * It provides methods that is used by the ProductController for fetching and adding products to the database.
 * It also handles the conversions between product DTOs and entities.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Find product from brand id list.
     *
     * @param id the id
     * @return the list products with the specified brand
     */
    public List<Product> findProductFromBrandId(Long id) {
        List<ProductDTO> productDTOS = getAllProducts()
            .stream()
            .filter(product -> product.getBrandID().equals(id))
            .toList();
        return productDTOS.stream()
                .map(this::convertToEntity)
                .toList();
    }

    /**
     * Find product from category id.
     *
     * @param categoryId the category id
     * @return the list of products in the specified category
     */
    public List<Product> findProductFromCategoryId(Long categoryId) {
        List<ProductDTO> productDTOs = getAllProducts()
                .stream()
                .filter(product -> product.getCategory().equals(categoryId))
                .toList();
        return productDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    /**
     * Fetches all products that is stored in the database.
     * @return a list of productDTO objects corresponding to all the products that are stored in the database.
     */
    public List<ProductDTO> getAllProducts() {
        // Fetches all the products and store them in a list.
        List<Product> products = productRepository.findAll();
        // Goes through the list of products and converts each product object to productDTO objects.
        // Then it collect and return all the converted products in a list.
        return products.stream()
                .map(product -> convertToDTO(product))
                .collect(Collectors.toList());
    }

    /**
     * Adds a new product to the database by using a productDTO object.
     * @param productDTO The productDTO object representing the product to be added.
     * @return A productDTO representation of the added product.
     */
    public ProductDTO addProduct(ProductDTO productDTO) {
        // Create new product object based on the provided productDTO.
        Product product = convertToEntity(productDTO);
        // Save this product in the database.
        Product savedProduct = productRepository.save(product);
        // Return the newly added product as a productDTO object.
        return convertToDTO(savedProduct);
    }

    /**
     * Fetches a specific productDTO based on the productID.
     * @param id The productID of the desired product.
     * @return A productDTO that matches the specified productID,
     * or an empty optional if no product with the specified productID was found.
     */
    public Optional<ProductDTO> getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(product -> convertToDTO(product));
    }

    /**
     * Deletes a product object from the database based on a specified productID.
     * @param id The specified productID of the product to be deleted.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no products with the specified ID are found.
     */
    public void deleteProduct(Long id) {
        // Check if any product with the specified ID exist.
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Product not found with ID: " + id);
        }
    }

    /**
     * Retrieves the description of a product by its name from the repository.
     * @param name the name of the product to retrieve the description for.
     * @return an Optional containing the description of the category if found,
     *         or an empty Optional if the category is not found
     */
    public Optional<String> getDescriptionFromName(String name) {
        // Retrieves a product with the specified name from the repository
        Optional<Product> productParam = productRepository.findAll()
                .stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();

        // Maps the product to its description if found
        return productParam.map(Product::getDescription);
    }


    /**
     * Converts productDTO object into a product entity object.
     * @param product The product object to convert.
     * @return  The converted product object.
     */
    private ProductDTO convertToDTO(Product product) {
        // Create new productDTO object to store the converted product object
        ProductDTO productDTO = new ProductDTO();
        // Manually set values for the DTO based on the values in the entity.
        productDTO.setProductID(product.getProductId());
        productDTO.setBrandID(product.getBrand().getBrandId());
        productDTO.setCategory(product.getCategory().getCategoryId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    /**
     * Converts productDTO object into a product entity object.
     * @param productDTO The product data transfer object to convert.
     * @return The converted product object.
     */
    private Product convertToEntity(ProductDTO productDTO) {
        // Create new product object to store the converted productDTO object
        Product product = new Product();
        // Using BeanUtils library for copying the values in the productDTO to the product.
        BeanUtils.copyProperties(productDTO, product);
        product.setProductId(productDTO.getProductID());
        Brand brand = new Brand();
        brand.setBrandId(productDTO.getBrandID());
        product.setBrand(brand);
        Category category = new Category();
        category.setCategoryId(productDTO.getCategory());
        product.setCategory(category);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
