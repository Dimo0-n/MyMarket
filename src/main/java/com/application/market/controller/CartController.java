package com.application.market.controller;

import com.application.market.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(){

        return "cart";
    }

    @PostMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") int id) {
        cartService.addToCart(id);
        return "redirect:/cart";
    }

}
