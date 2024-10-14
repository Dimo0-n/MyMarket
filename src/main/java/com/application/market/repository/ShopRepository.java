package com.application.market.repository;

import com.application.market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Product, Long> {
}
