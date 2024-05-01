package com.electromart.electromart.Controller;

import com.electromart.electromart.entities.Brand;
import com.electromart.electromart.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<Brand> fetchAllBrands() {
        return brandService.getAllBrands();
    }
}
