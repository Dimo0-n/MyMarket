package com.application.market.service;

import com.application.market.entity.Product;
import com.application.market.entity.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopService {

    Page<ProductDto> getAllProducts(Pageable pageable);

}
