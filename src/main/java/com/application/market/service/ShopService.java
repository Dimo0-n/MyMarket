package com.application.market.service;

import com.application.market.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopService {

    Page<ProductDto> getAllProducts(Pageable pageable);
    List<ProductDto> findBestSellingProducts();
    Page<ProductDto> findAllProductsByCategory(Pageable pageable, int id);
    List<ProductDto> getFeaturedProducts();
    Page<ProductDto> getProductsByCategory(String categoryName, Pageable pageable);

}
