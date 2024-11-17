package com.application.market.repository;

import com.application.market.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from products order by votes_count desc", nativeQuery = true)
    List<Product> findBestSellingProducts();

    @Query(value = "SELECT * FROM products WHERE category_id = :id", nativeQuery = true)
    Page<Product> findAllProductsByCategory(@Param("id") int id, Pageable pageable);

}
