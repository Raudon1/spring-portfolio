package com.cac.portfolio.repo;

import com.cac.portfolio.domain.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogueRepo extends JpaRepository<Catalogue, Long> {
    Catalogue findByName(String name);
}
