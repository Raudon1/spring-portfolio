package com.cac.portfolio.service;

import com.cac.portfolio.domain.Catalogue;
import com.cac.portfolio.domain.Category;
import com.cac.portfolio.repo.CatalogueRepo;
import com.cac.portfolio.repo.CategoryRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CatalogueServiceImplTest {

    @Mock
    private CatalogueRepo catalogueRepo;

    @Mock
    private CategoryRepo categoryRepo;

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @InjectMocks
    private CatalogueServiceImpl catalogueServiceImpl;

    private java.sql.Date parseDate(String date) {
        try {
            return new Date(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Test
    void test_saveCatalogue() {
          //Given
          String expected = "Test1";

          Catalogue catalogue = new Catalogue();
          catalogue.setId(99L);
          catalogue.setName(expected);
          catalogue.setDuration(parseDate("2022-09-29"));
          catalogue.setCategories(new ArrayList<>());
          when(catalogueRepo.save(catalogue)).thenReturn(catalogue);
          //When
          Catalogue obtainedCatalogue = catalogueServiceImpl.saveCatalogue(catalogue);

          //Then
          Assertions.assertThat(obtainedCatalogue.getName()).isEqualTo(expected);

    }

    @Test
    void test_addCategoryToCatalogue() {
        //given
        Catalogue catalogue = Mockito.mock(Catalogue.class);
        Category category = new Category();
        Collection<Category> categories = new ArrayList<Category>();
        when(catalogue.getCategories()).thenReturn(categories);
        when(catalogueRepo.findByName("testCatalogue")).thenReturn(catalogue);
        when(categoryRepo.findByName("testCategory")).thenReturn(category);
        //When
        Boolean test = catalogueServiceImpl.addCategoryToCatalogue("testCategory","testCatalogue");
        //Then
        Assertions.assertThat(test).isTrue();
    }

    @Test
    void test_getCatalogue() {
        //BDD Behavior Driven Development
        //given dado que
        String expected = "testCatalogue";
        Catalogue catalogue = new Catalogue();
        catalogue.setName(expected);
        when(catalogueRepo.findByName(expected)).thenReturn(catalogue);
        //When cuando haga esto
        Catalogue obtainedCatalogue = catalogueServiceImpl.getCatalogue(expected);

        //Then esto tiene que ser verdad
        Assertions.assertThat(obtainedCatalogue.getName()).isEqualTo(expected);
    }

    @Test
    void test_getCatalogues() {
        //given
        List<Catalogue> catalogueList = new ArrayList<>();
        when(catalogueRepo.findAll()).thenReturn(catalogueList);
        //when
        List<Catalogue> catalogues = catalogueServiceImpl.getCatalogues();
        //Then
        Assertions.assertThat(catalogues).isNotNull();
    }
}