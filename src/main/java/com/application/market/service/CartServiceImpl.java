package com.application.market.service;

import com.application.market.entity.CartItems;
import com.application.market.entity.Product;
import com.application.market.entity.User;
import com.application.market.repository.CartRepository;
import com.application.market.repository.ShopRepository;
import com.application.market.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addToCart(int id, int quantity, String email) {
        Optional<Product> product = shopRepository.findById((long) id);
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.get();

        CartItems cartItem = new CartItems();

        cartItem.setCategory(product.get().getCategory());
        cartItem.setDiscount(product.get().getDiscount());
        cartItem.setPrice(product.get().getProductPrice());
        cartItem.setBase64Image(product.get().getBase64Image());
        cartItem.setProductName(product.get().getProductName());
        cartItem.setQuantity(quantity);
        cartItem.setUser(user);

        cartRepository.save(cartItem);

    }

    @Override
    public List<CartItems> getAllFromCart(String email) {

        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.get();

        List<CartItems> cartItems = cartRepository.getAllFromCart(user.getId());

        return cartItems;

    }

}
