package com.cac.portfolio.api;

import com.cac.portfolio.domain.Category;
import com.cac.portfolio.service.CategoryService;
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
public class CategoryResource {
    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategory() {
        return ResponseEntity.ok().body(categoryService.getCategories());
    }

    @PostMapping("/category/save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/category/save").toUriString());
        return ResponseEntity.created(null).body(categoryService.saveCategory(category));
    }

    @PostMapping("/category/addproducttocategory")
    public ResponseEntity<?> addProductToCategory(@RequestBody ProductToCategoryForm form) {
        categoryService.addProductToCategory(form.getCategoryName(), form.getProductName());
        return ResponseEntity.ok().build();
    }


    @Data
    class ProductToCategoryForm {
        private String categoryName;
        private String productName;

    }
}

