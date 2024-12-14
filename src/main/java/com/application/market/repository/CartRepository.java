package com.application.market.repository;

import com.application.market.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItems, Integer> {

    @Query(value = "select * from cart_items where user_id = :id", nativeQuery = true)
    List<CartItems> getAllFromCart(Long id);

}
