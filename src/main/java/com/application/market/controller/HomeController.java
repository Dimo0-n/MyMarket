package com.application.market.controller;

import com.application.market.entity.Category;
import com.application.market.entity.Product;
import com.application.market.entity.ProductDto;
import com.application.market.service.CategoryService;
import com.application.market.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ShopService shopService;

    @GetMapping("/index")
    public String homeController(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<ProductDto> bestSellingProducts = shopService.findBestSellingProducts();
        List<ProductDto> featuredProducts = shopService.getFeaturedProducts();

        model.addAttribute("categories", categories);
        model.addAttribute("bestSellingProducts", bestSellingProducts);
        model.addAttribute("featuredProducts", featuredProducts);
        return "index";
    }

}
