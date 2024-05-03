package com.electromart.electromart.controller;

import com.electromart.electromart.entity.Category;
import com.electromart.electromart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<Category> fetchAllCategories() {
        return categoryService.getAllCategories();
    }
}
