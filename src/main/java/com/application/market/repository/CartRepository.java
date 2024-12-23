package com.application.market.repository;

import com.application.market.entity.CartItems;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItems, Integer> {

    @Query(value = "select * from cart_items where email =:email", nativeQuery = true)
    List<CartItems> getAllFromCart(String email);

    @Modifying
    @Query(value = "delete from cart_items where email =:email", nativeQuery = true)
    @Transactional
    void deleteProductsFromCart(@Param("email") String email);

    void deleteById(int id);

}
