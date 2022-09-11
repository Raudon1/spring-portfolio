package com.cac.portfolio.service;

import com.cac.portfolio.domain.Category;
import com.cac.portfolio.domain.Products;
import com.cac.portfolio.repo.CategoryRepo;
import com.cac.portfolio.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    @Override
    public Category saveCategory(Category category) {
        log.info("Saving new category {} to the database", category.getName());
        return categoryRepo.save(category);
    }

    @Override
    public Boolean addProductToCategory(String productName, String categoryName) {
        log.info("Adding product {} to category {}", productName, categoryName);
        Category category = categoryRepo.findByName(categoryName);
        Products products = productRepo.findByName(productName);
        return category.getProducts().add(products);
    }


    @Override
    public Category getCategory(String categoryName) {
        log.info("Fetching categories {}", categoryName);
        return categoryRepo.findByName(categoryName);
    }

    @Override
    public List<Category> getCategories() {
        log.info("Fetching all categories");
        return categoryRepo.findAll();
    }
}

