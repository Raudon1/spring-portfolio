package com.cac.portfolio.service;


import com.cac.portfolio.domain.Products;
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
public class ProductsServiceImpl implements ProductsService{
    private final ProductRepo productRepo;

    @Override
    public Products saveProducts(Products products) {
        log.info("Saving new client {} to the database", products.getName());
        return productRepo.save(products);
    }

    @Override
    public Products getProduct(String productName) {
        log.info("Fetching products {}", productName);
        return productRepo.findByName(productName);
    }

    @Override
    public List<Products> getProducts() {
        log.info("Fetching all products");
        return productRepo.findAll();
    }
}
