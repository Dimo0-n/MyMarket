package com.application.market.service;

import com.application.market.entity.CartItems;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

public interface CartService {

    void addToCart(int id, int quantity, String email);

    Map<String, Object> getAllFromCart(String email);

    void deleteProductsFromCart(String email);

    void deleteCartItem(int id);

    void updateQuantity(int id, int quantity);

}
