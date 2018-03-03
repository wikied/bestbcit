package com.scriptofan.ecommerce.Platforms.Ebay;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("get-categories")
public class CategoryRetrievalController {

    private CategoryRetrievalService categoryRetrievalService;

    public CategoryRetrievalController() {
        this.categoryRetrievalService = new CategoryRetrievalService();
    }

    @GetMapping
    public String getCategories() {
        return categoryRetrievalService.getCategories().toString().replace("], [", "],<br />[");
    }
}
