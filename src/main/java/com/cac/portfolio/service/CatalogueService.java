package com.cac.portfolio.service;

import com.cac.portfolio.domain.Catalogue;

import java.util.List;

public interface CatalogueService {
    Catalogue saveCatalogue(Catalogue catalogue);
    void addCategoryToCatalogue (String categoryName, String catalogueName);
    Catalogue getCatalogue (String catalogueName);
    List<Catalogue> getCatalogues();
}

