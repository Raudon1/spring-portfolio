package com.cac.portfolio.repo;

import com.cac.portfolio.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Products, Long> {
    Products findByName(String productName);
}
