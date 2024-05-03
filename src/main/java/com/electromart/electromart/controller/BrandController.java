package com.electromart.electromart.controller;

import com.electromart.electromart.entity.Brand;
import com.electromart.electromart.service.BrandService;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Brand controller.
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * Fetch all brands list.
     *
     * @return the list
     */
    @GetMapping({"", "/"})
    public List<Brand> fetchAllBrands() {
        return brandService.getAllBrands();
    }

    /**
     * Fetch brand name response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/brand_id={id}")
    public ResponseEntity<String> fetchBrandName(@PathVariable(value = "id", required = false)
                                                     Long id) {
        Optional<String> brandNameParam = brandService.getNameFromID(id);
        if (id != null && id > 0) {
            return brandNameParam.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The brand with id " + id + " Was not found"));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No valid id was specified.");
        }
    }

    /**
     * Fetch brand description response entity.
     *
     * @param name the name
     * @return the response entity
     */
    @GetMapping("/name={name}")
    public ResponseEntity<String> fetchBrandDescription(@PathVariable(
        value = "name", required = false) String name) {
        Optional<String> brandNameParam = brandService.getDescriptionFromName(name);
        if (!name.isEmpty()) {
            return brandNameParam.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The brand with name " + name + " Was not found"));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No valid brand name was specified. To add a new brand " +
                    "use the HTTP POST method.");
        }
    }
}
