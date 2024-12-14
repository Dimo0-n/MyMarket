package com.application.market.controller;

import com.application.market.entity.CartItems;
import com.application.market.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<CartItems> cartItems = cartService.getAllFromCart(email);
        model.addAttribute("cartItems", cartItems);

        return "cart";
    }

    @PostMapping("/addToCart/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable int productId, @RequestBody CartItems request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        cartService.addToCart(productId, request.getQuantity(), email);
        return ResponseEntity.ok("Product added to cart");
    }



}
