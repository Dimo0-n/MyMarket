package com.application.market.service;

import com.application.market.controller.UserController;
import com.application.market.entity.CartItems;
import com.application.market.entity.Product;
import com.application.market.entity.User;
import com.application.market.repository.CartRepository;
import com.application.market.repository.ShopRepository;
import com.application.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private UserController userController;

    @Override
    public void addToCart(int id, int quantity, String email) {

        Optional<Product> productOptional = shopRepository.findById((long) id);
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Produsul cu ID-ul specificat nu există.");
        }

        List<CartItems> cartItems = getAllFromCart(email);

        Product product = productOptional.get();

        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.get();

        CartItems cartItem = new CartItems();
        cartItem.setCategory(product.getCategory());
        cartItem.setDiscount(product.getDiscount());
        cartItem.setPrice(product.getProductPrice());
        cartItem.setBase64Image(product.getBase64Image());
        cartItem.setProductName(product.getProductName());
        cartItem.setQuantity(quantity);
        cartItem.setUser(user);

        cartRepository.save(cartItem);
    }

    @Override
    public List<CartItems> getAllFromCart(String email) {
        List<CartItems> cartItems = cartRepository.getAllFromCart(email);
        return cartItems;

    }

    @Override
    public void deleteProductsFromCart(String email) {
        cartRepository.deleteProductsFromCart(email);
    }

    public void deleteCartItem(int id){
        cartRepository.deleteById(id);
    }

    @Override
    public void updateQuantity(int id, int quantity) {
        CartItems cartItem = cartRepository.getById(id);
        cartItem.setQuantity(quantity);

        cartRepository.save(cartItem);
    }

}
