package com.electromart.electromart.controller;

import com.electromart.electromart.entity.Promotion;
import com.electromart.electromart.service.ProductReviewService;
import com.electromart.electromart.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("")
    public List<Promotion> fetchAllPromotions() {
        return promotionService.getAllPromotions();
    }
}
