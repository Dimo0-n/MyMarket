package com.application.market.controller;

import com.application.market.entity.CardData;
import com.application.market.entity.CartItems;
import com.application.market.service.CartService;
import com.application.market.service.CartServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Map<String, Object> items =  cartService.getAllFromCart(email);

        model.addAttribute("cartItems", items.get("cartItems"));
        session.setAttribute("totalPrice", items.get("totalPrice"));

        return "cart";
    }

    @PostMapping("/addToCart/{productId}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> addToCart(
            @PathVariable int productId,
            @RequestBody CartItems request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Map<String, String> response = new HashMap<>();
        if (email.equals("anonymousUser")) {
            response.put("message", "Loghează-te pentru a adăuga produse în coș!");
            response.put("messageType", "error");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } else {
            cartService.addToCart(productId, request.getQuantity(), email);
            response.put("message", "Produsul a fost adăugat în coș!");
            response.put("messageType", "success");
            return ResponseEntity.ok(response);
        }
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
