package com.application.market.service;

import com.application.market.entity.Product;
import com.application.market.entity.ProductDto;
import com.application.market.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{


    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> productsPage = shopRepository.findAll(pageable); // Obține pagina de produse

        // Mapează fiecare Product în ProductDto
        return productsPage.map(product -> {
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

            return productDto;
        });
    }

    @Override
    public List<ProductDto> findBestSellingProducts() {

        List<Product> products = shopRepository.findBestSellingProducts();
        List<ProductDto> productDtos = new ArrayList<>();

        // Limitează lista de produse la 10
        List<Product> limitedProducts = products.stream()
                .limit(10)
                .toList();

        for (Product product : limitedProducts) {
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

    @Override
    public Page<ProductDto> findAllProductsByCategory(Pageable pageable, int id) {
        Page<Product> products = shopRepository.findAllProductsByCategory(id, pageable); // Obține pagina de produse

        return products.map(product -> {
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

            return productDto;
        });
    }


}
