package com.application.market.service;

import com.application.market.entity.Product;
import com.application.market.entity.ProductDto;
import com.application.market.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{


    @Autowired
    private ShopRepository shopRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = shopRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>(); // Lista pentru DTO-uri

        for(Product product : products){
            Double pretCuDiscount = product.getProductPrice() * (100 - product.getDiscount()) / 100;
            Double pretFormatat = Math.round(pretCuDiscount * 10.0) / 10.0;

            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setCategory(product.getCategory());
            productDto.setProductName(product.getProductName());
            productDto.setProductDescription(product.getProductDescription());
            productDto.setProductPrice(product.getProductPrice());
            productDto.setDiscount(product.getDiscount());
            productDto.setRating(product.getRating());
            productDto.setVotesCount(product.getVotesCount());
            productDto.setImage(product.getImage());
            productDto.setBase64Image(product.getBase64Image());
            productDto.setProductDiscountPrice(String.valueOf(pretFormatat));

            productDtos.add(productDto);
        }

        return productDtos;
    }



}
