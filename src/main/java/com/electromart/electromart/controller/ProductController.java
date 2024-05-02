package com.electromart.electromart.Controller;

import com.electromart.electromart.entity.Product;
import com.electromart.electromart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public List<Product> fetchAllProducts() {
        return productService.getAllProducts();
    }

}
