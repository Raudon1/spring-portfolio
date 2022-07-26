package com.cac.portfolio.repo;

import com.cac.portfolio.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepo extends JpaRepository<Shop, Long> {
    Shop findByName(String shopName);
}
