package com.electromart.electromart.Controller;

import com.electromart.electromart.entities.Category;
import com.electromart.electromart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> fetchAllCategories() {
        return categoryService.getAllCategories();
    }
}
