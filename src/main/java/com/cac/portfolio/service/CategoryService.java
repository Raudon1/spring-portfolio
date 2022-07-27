package com.cac.portfolio.service;

import com.cac.portfolio.domain.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory (Category category);
    void addProductToCategory (String productName, String categoryName);
    Category getCategory (String categoryName);
    List<Category> getCategories();
}
