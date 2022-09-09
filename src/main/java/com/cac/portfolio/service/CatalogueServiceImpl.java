package com.cac.portfolio.service;

import com.cac.portfolio.domain.Catalogue;
import com.cac.portfolio.domain.Category;
import com.cac.portfolio.repo.CatalogueRepo;
import com.cac.portfolio.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CatalogueServiceImpl implements CatalogueService{
    private final CatalogueRepo catalogueRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public Catalogue saveCatalogue(Catalogue catalogue) {
        log.info("Saving new catalogue {} to the database", catalogue.getName());
        return catalogueRepo.save(catalogue);
    }

    @Override
    public Boolean addCategoryToCatalogue(String categoryName, String catalogueName) {
        log.info("Adding category {} to catalogue {}", categoryName, catalogueName );
        Catalogue catalogue = catalogueRepo.findByName(catalogueName);
        Category category = categoryRepo.findByName(categoryName);
        return catalogue.getCategories().add(category);
    }

    @Override
    public Catalogue getCatalogue(String catalogueName) {
        log.info("Fetching catalogues {}", catalogueName);
        return catalogueRepo.findByName(catalogueName);
    }

    @Override
    public List<Catalogue> getCatalogues() {
        log.info("Fetching all catalogues");
        return catalogueRepo.findAll();
    }
}
