package com.cac.portfolio.service;

import com.cac.portfolio.domain.Catalogue;
import com.cac.portfolio.domain.Client;
import com.cac.portfolio.domain.DeliveryMan;
import com.cac.portfolio.domain.Shop;
import com.cac.portfolio.repo.CatalogueRepo;
import com.cac.portfolio.repo.ClientRepo;
import com.cac.portfolio.repo.DeliveryMRepo;
import com.cac.portfolio.repo.ShopRepo;
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
class ShopServiceImplTest {
    @Mock
    private ShopRepo shopRepo;

    @Mock
    private ClientRepo clientRepo;

    @Mock
    private DeliveryMRepo deliveryMRepo;

    @Mock
    private CatalogueRepo catalogueRepo;

    @InjectMocks
    private ShopServiceImpl shopServiceImpl;

    @Test
    void test_saveShop() {
        //Given
        String expected = "testS";
        Shop shop = new Shop();
        shop.setName(expected);
        when(shopRepo.save(shop)).thenReturn(shop);
        //When
        Shop obtainedShop = shopServiceImpl.saveShop(shop);
        //Then
        Assertions.assertThat(obtainedShop.getName()).isEqualTo(expected);
    }

    @Test
    void test_addClientToShop() {
        //Given
        String expectedShop = "testS";
        String expectedClient = "testC";
        Shop shop = Mockito.mock(Shop.class);
        Client client = new Client();
        Collection<Client> clientList = new ArrayList<>();
        when(shop.getClients()).thenReturn(clientList);
        when(shopRepo.findByName(expectedShop)).thenReturn(shop);
        when(clientRepo.findByName(expectedClient)).thenReturn(client);
        //When
        Boolean test = shopServiceImpl.addClientToShop(expectedClient,expectedShop);
        //Then
        Assertions.assertThat(test).isTrue();
    }

    @Test
    void test_addDeliverMenToShop() {
        //Given
        String expectedShop = "testS";
        String expectedDeliveryMan = "testD";
        Shop shop = Mockito.mock(Shop.class);
        DeliveryMan deliveryMan = new DeliveryMan();
        Collection<DeliveryMan> deliveryMENList = new ArrayList<>();
        when(shop.getDeliveryMEN()).thenReturn(deliveryMENList);
        when(shopRepo.findByName(expectedShop)).thenReturn(shop);
        when(deliveryMRepo.findByName(expectedDeliveryMan)).thenReturn(deliveryMan);
        //When
        Boolean test = shopServiceImpl.addDeliverMenToShop(expectedDeliveryMan,expectedShop);
        //Then
        Assertions.assertThat(test).isTrue();
    }

    @Test
    void test_addCatalogueToShop() {
        //Given
        String expectedShop = "testS";
        String expectedCatalogue = "testC";
        Shop shop = Mockito.mock(Shop.class);
        Catalogue catalogue = new Catalogue();
        Collection<Catalogue> cataloguesList = new ArrayList<>();
        when(shop.getCatalogues()).thenReturn(cataloguesList);
        when(shopRepo.findByName(expectedShop)).thenReturn(shop);
        when(catalogueRepo.findByName(expectedCatalogue)).thenReturn(catalogue);
        //When
        Boolean test = shopServiceImpl.addCatalogueToShop(expectedCatalogue,expectedShop);
        //Then
        Assertions.assertThat(test).isTrue();
    }

    @Test
    void test_getShop() {
        //Given
        String expected = "testS";
        Shop shop = new Shop();
        shop.setName(expected);
        when(shopRepo.findByName(expected)).thenReturn(shop);
        //When
        Shop obtainedShop = shopServiceImpl.getShop(expected);
        //Then
        Assertions.assertThat(obtainedShop.getName()).isEqualTo(expected);
    }

    @Test
    void test_getShops() {
        //Given
        List<Shop> shopList = new ArrayList<>();
        when(shopRepo.findAll()).thenReturn(shopList);
        //When
        List<Shop> obtainedShopList = shopServiceImpl.getShops();
        //Then
        Assertions.assertThat(obtainedShopList).isNotNull();
    }
}