package com.application.market.service;

import com.application.market.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

}
