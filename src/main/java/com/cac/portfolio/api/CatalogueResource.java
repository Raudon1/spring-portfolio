package com.cac.portfolio.api;

import com.cac.portfolio.domain.Catalogue;
import com.cac.portfolio.service.CatalogueService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CatalogueResource {
    private final CatalogueService catalogueService;

    @GetMapping("/catalogue")
    public ResponseEntity<List<Catalogue>> getCatalogue() {
        return ResponseEntity.ok().body(catalogueService.getCatalogues());
    }

    @PostMapping("/catalogue/save")
    public ResponseEntity<Catalogue> saveCatalogue(@RequestBody Catalogue catalogue) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/catalogue/save").toUriString());
        return ResponseEntity.created(null).body(catalogueService.saveCatalogue(catalogue));
    }

    @PostMapping("/role/addcategorytocatalogue")
    public ResponseEntity<?> addCategoryToCatalogue(@RequestBody CategoryToCatalogue form) {
        catalogueService.addCategoryToCatalogue(form.getCategoryName(), form.getCatalogueName());
        return ResponseEntity.ok().build();
    }

    @Data
    class CategoryToCatalogue {
        private String catalogueName;
        private String categoryName;

    }

}

