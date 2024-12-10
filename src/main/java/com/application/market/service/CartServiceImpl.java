package com.application.market.service;

import com.application.market.entity.CartItems;
import com.application.market.entity.Product;
import com.application.market.repository.CartRepository;
import com.application.market.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public void addToCart(int id){
        Optional<Product> product = shopRepository.findById((long) id);

        CartItems cartItem = new CartItems();

        cartItem.setCategory(product.get().getCategory());
        cartItem.setDiscount(product.get().getDiscount());
        cartItem.setPrice(product.get().getProductPrice());

        cartRepository.save(cartItem);

    }

}
