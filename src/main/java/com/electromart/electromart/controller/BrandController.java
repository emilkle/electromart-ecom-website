package com.electromart.electromart.controller;

import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.service.BrandService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping({"", "/"})
    public List<Brand> fetchAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/brand_id={id}")
    public ResponseEntity<String> fetchBrandName(@PathVariable("id") Long id) {
        if (id != null) {
            Optional<String> brandNameParam = brandService.getNameByID(id);
            return brandNameParam.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The brand with id " + id + " Was not found"));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No id was specified.");
        }
    }
}
