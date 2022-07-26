package com.cac.portfolio.repo;

import com.cac.portfolio.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
}
