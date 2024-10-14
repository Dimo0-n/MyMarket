package com.application.market.service;

import com.application.market.entity.Product;
import com.application.market.entity.ProductDto;

import java.util.List;

public interface ShopService {

    List<ProductDto> getAllProducts();

}
