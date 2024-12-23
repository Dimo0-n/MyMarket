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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    public String addToCart(@PathVariable int productId, @RequestBody CartItems request, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (email == null || email.equals("anonymousUser")) {
            redirectAttributes.addFlashAttribute("message", "Log in first!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/cart";
        }

        cartService.addToCart(productId, request.getQuantity(), email);
        redirectAttributes.addFlashAttribute("message", "Product added to cart!");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/cart";
    }

    @GetMapping("/all-products-remove")
    public String deleteProductsFromCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        cartService.deleteProductsFromCart(email);

        return "cart";
    }

    @PostMapping("/delete-cart-item-{id}")
    public String deleteCartItem(@PathVariable int id) {
        cartService.deleteCartItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/update-quantity-{id}")
    public String updateQuantity(@PathVariable int id, @RequestParam("quantity") int quantity) {
        cartService.updateQuantity(id, quantity);
        return "redirect:/cart";
    }

}
