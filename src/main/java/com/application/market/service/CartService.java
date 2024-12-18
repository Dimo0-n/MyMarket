package com.application.market.service;

import com.application.market.entity.CartItems;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CartService {

    void addToCart(int id, int quantity, String email);

    List<CartItems> getAllFromCart(String email);

    void deleteProductsFromCart(String email);

}
