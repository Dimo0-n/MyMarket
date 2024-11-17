package com.application.market.controller;

import com.application.market.entity.Product;
import com.application.market.entity.ProductDto;
import com.application.market.service.ShopService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import com.application.market.entity.Category;
import com.application.market.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ShopService shopService;

    @GetMapping("/shop")
    public String shop(@RequestParam(defaultValue = "0") int page, Model model) {
        List<Category> categories = categoryService.getAllCategories();

        Pageable pageable = (Pageable) PageRequest.of(page, 20); // 20 produse pe pagină
        Page<ProductDto> productPage = shopService.getAllProducts(pageable); // Modifică serviciul pentru a suporta paginarea

        model.addAttribute("categories", categories);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "shop";
    }

    @GetMapping("/shop-{id}")
    public String shopByCategory(@RequestParam(defaultValue = "0") int page, @PathVariable int id, Model model) {
        List<Category> categories = categoryService.getAllCategories();

        Pageable pageable = PageRequest.of(page, 20); // 20 produse pe pagină
        Page<ProductDto> productPage = shopService.findAllProductsByCategory(pageable, id); // Returnează un Page

        model.addAttribute("categories", categories);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "shop";
    }



}
