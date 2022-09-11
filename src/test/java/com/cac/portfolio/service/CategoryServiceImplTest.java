package com.cac.portfolio.service;

import com.cac.portfolio.domain.Category;
import com.cac.portfolio.domain.Products;
import com.cac.portfolio.repo.CategoryRepo;
import com.cac.portfolio.repo.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepo categoryRepo;
    @Mock
    private ProductRepo productRepo;
    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    void test_saveCategory() {
        //Given
        String expected = "TestC1";
        Category category = new Category();
        category.setId(89L);
        category.setName(expected);
        category.setProducts(new ArrayList<>());
        when(categoryRepo.save(category)).thenReturn(category);
        //When
        Category obtainedCategory = categoryServiceImpl.saveCategory(category);
        //Then
        Assertions.assertThat(obtainedCategory.getName()).isEqualTo(expected);
    }

    @Test
    void test_addProductToCategory() {
        //Given
        String expectedCategory = "TestC";
        String expectedProduct = "TestP";
        Category category = Mockito.mock(Category.class);
        Products products = new Products();
        Collection<Products> productsL= new ArrayList<>();
        when(category.getProducts()).thenReturn(productsL);
        when(categoryRepo.findByName(expectedCategory)).thenReturn(category);
        when(productRepo.findByName(expectedProduct)).thenReturn(products);
        //When
        Boolean test = categoryServiceImpl.addProductToCategory(expectedProduct,expectedCategory);
        //Then
        Assertions.assertThat(test).isTrue();
    }

    @Test
    void test_getCategory() {
        //Given
        String expectedCategory = "TestC";
        Category category = new Category();
        category.setName(expectedCategory);
        when(categoryRepo.findByName(expectedCategory)).thenReturn(category);
        //When
        Category obtaninedCategory = categoryServiceImpl.getCategory(expectedCategory);
        //Then
        Assertions.assertThat(obtaninedCategory.getName()).isEqualTo(expectedCategory);
    }

    @Test
    void test_getCategories() {
        //Given
        List<Category> categoryList = new ArrayList<>();
        when(categoryRepo.findAll()).thenReturn(categoryList);
        //When
        List<Category> categories = categoryServiceImpl.getCategories();
        //Then
        Assertions.assertThat(categories).isNotNull();
    }
}