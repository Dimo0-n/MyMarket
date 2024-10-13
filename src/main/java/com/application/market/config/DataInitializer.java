package com.application.market.config;

import com.application.market.entity.Category;
import com.application.market.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final CategoryRepository categoryRepository;

    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private byte[] loadImage(String imagePath) {
        try {
            ClassPathResource resource = new ClassPathResource(imagePath);
            InputStream inputStream = resource.getInputStream();
            return inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostConstruct
    public void init() {
        addCategories();

    }

    private void addCategories() {

        List<Category> categoriesList = List.of(
                new Category(1, "Fruits & Veges",
                        loadImage("static/images/category-thumb-1.jpg")),
                new Category(2, "Breads & Sweets",
                        loadImage("static/images/category-thumb-2.jpg")),
                new Category(3, "Wine",
                        loadImage("static/images/category-thumb-3.jpg")),
                new Category(4, "Beverages",
                        loadImage("static/images/category-thumb-4.jpg")),
                new Category(5, "Meat Products",
                        loadImage("static/images/category-thumb-5.jpg")),
                new Category(6, "Breads",
                        loadImage("static/images/category-thumb-6.jpg")),
                new Category(7, "Dairy & Eggs",
                        loadImage("static/images/category-thumb-7.jpg")),
                new Category(8, "Breads & Sweets",
                        loadImage("static/images/category-thumb-8.jpg")),
                new Category(9, "Breads & Sweets",
                        loadImage("static/images/category-thumb-1.jpg")),
                new Category(10, "Breads & Sweets",
                        loadImage("static/images/category-thumb-2.jpg")),
                new Category(11, "Breads & Sweets",
                        loadImage("static/images/category-thumb-3.jpg")),
                new Category(12, "Breads & Sweets",
                        loadImage("static/images/category-thumb-4.jpg")),
                new Category(13, "Breads & Sweets",
                        loadImage("static/images/category-thumb-5.jpg")),
                new Category(14, "Breads & Sweets",
                        loadImage("static/images/category-thumb-6.jpg")),
                new Category(15, "Breads & Sweets",
                        loadImage("static/images/category-thumb-7.jpg"))
        );

        categoryRepository.saveAll(categoriesList);

    }

    private void addProducts(){

        //repository.save
    }

}
