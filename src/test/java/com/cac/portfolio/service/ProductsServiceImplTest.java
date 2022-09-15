package com.cac.portfolio.service;

import com.cac.portfolio.domain.Products;
import com.cac.portfolio.repo.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductsServiceImplTest {
    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductsServiceImpl productsServiceImpl;

    @Test
    void test_saveProducts() {
        //Given
        String expected = "testP";
        Products products = new Products();
        products.setName(expected);
        when(productRepo.save(products)).thenReturn(products);
        //When
        Products obtainedProduct = productsServiceImpl.saveProducts(products);
        //Then
        Assertions.assertThat(obtainedProduct.getName()).isEqualTo(expected);

    }

    @Test
    void test_getProduct() {
        //Given
        String expected = "testP";
        Products products = new Products();
        products.setName(expected);
        when(productRepo.findByName(expected)).thenReturn(products);
        //When
        Products obtainedProduct = productsServiceImpl.getProduct(expected);
        //Then
        Assertions.assertThat(obtainedProduct.getName()).isEqualTo(expected);
    }

    @Test
    void test_getProducts() {
        //Given
        List<Products> productsList = new ArrayList<>();
        when(productRepo.findAll()).thenReturn(productsList);
        //When
        List<Products> obtainedProductList = productsServiceImpl.getProducts();
        //Then
        Assertions.assertThat(obtainedProductList).isNotNull();
    }
}