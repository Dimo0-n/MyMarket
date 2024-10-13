package com.application.market.controller;

import org.springframework.ui.Model;
import com.application.market.entity.Category;
import com.application.market.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/shop")
    public String shop(Model model) {

        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("categories", categories);
        return "shop";
    }

}
