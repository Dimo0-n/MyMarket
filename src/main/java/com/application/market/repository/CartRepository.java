package com.application.market.repository;

import com.application.market.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItems, Integer> {
}
