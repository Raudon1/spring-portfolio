package com.cac.portfolio.api;

import com.cac.portfolio.domain.Products;
import com.cac.portfolio.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductResource {
    private final ProductsService productsService;

    @GetMapping("/products")
    public ResponseEntity<List<Products>> getProducts() {
        return ResponseEntity.ok().body(productsService.getProducts());
    }

    @PostMapping("/products/save")
    public ResponseEntity<Products> saveProducts(@RequestBody Products products) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/products/save").toUriString());
        return ResponseEntity.created(null).body(productsService.saveProducts(products));
    }

}
