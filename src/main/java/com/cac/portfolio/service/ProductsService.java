package com.cac.portfolio.service;

import com.cac.portfolio.domain.Products;

import java.util.List;

public interface ProductsService {
    Products saveProducts (Products products);
    Products getProducts (String productName);
    List<Products> getProducts();
}
