package com.application.market.service;

import com.application.market.entity.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopService {

    Page<ProductDto> getAllProducts(Pageable pageable);
    List<ProductDto> findBestSellingProducts();
    Page<ProductDto> findAllProductsByCategory(Pageable pageable, int id);
    List<ProductDto> getFeaturedProducts();

}
