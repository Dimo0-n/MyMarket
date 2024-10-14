package com.application.market.controller;

import com.application.market.entity.Product;
import com.application.market.entity.ProductDto;
import com.application.market.service.ShopService;
import org.springframework.ui.Model;
import com.application.market.entity.Category;
import com.application.market.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ShopService shopService;

    @GetMapping("/shop")
    public String shop(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<ProductDto> products = shopService.getAllProducts();

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);

        return "shop";
    }


}
