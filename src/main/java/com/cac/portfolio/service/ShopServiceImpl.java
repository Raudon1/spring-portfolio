package com.cac.portfolio.service;

import com.cac.portfolio.domain.Catalogue;
import com.cac.portfolio.domain.Client;
import com.cac.portfolio.domain.DeliveryMan;
import com.cac.portfolio.domain.Shop;
import com.cac.portfolio.repo.CatalogueRepo;
import com.cac.portfolio.repo.ClientRepo;
import com.cac.portfolio.repo.DeliveryMRepo;
import com.cac.portfolio.repo.ShopRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShopServiceImpl implements ShopService {
    private final ClientRepo clientRepo;
    private final ShopRepo shopRepo;
    private final DeliveryMRepo deliveryMRepo;
    private final CatalogueRepo catalogueRepo;

    @Override
    public Shop saveShop(Shop shop) {
        log.info("Saving new shop {} to the database", shop.getName());
        return shopRepo.save(shop);
    }
    @Override
    public Boolean addClientToShop(String clientName, String shopName) {
        log.info("Adding client {} to shop {}", clientName, shopName);
        Shop shop = shopRepo.findByName(shopName);
        Client client = clientRepo.findByName(clientName);
        return shop.getClients().add(client);
    }

    @Override
    public Boolean addDeliverMenToShop(String deliverName, String shopName) {
        log.info("Adding client {} to shop {}", deliverName, shopName);
        Shop shop = shopRepo.findByName(shopName);
        DeliveryMan deliveryMan = deliveryMRepo.findByName(deliverName);
        return shop.getDeliveryMEN().add(deliveryMan);
    }

    @Override
    public Boolean addCatalogueToShop(String catalogueName, String shopName) {
        log.info("Adding client {} to shop {}", catalogueName, shopName);
        Shop shop = shopRepo.findByName(shopName);
        Catalogue catalogue = catalogueRepo.findByName(catalogueName);
        return shop.getCatalogues().add(catalogue);
    }

    @Override
    public Shop getShop(String shopName) {
        log.info("Fetching shops {}", shopName);
        return shopRepo.findByName(shopName);
    }

    @Override
    public List<Shop> getShops() {
        log.info("Fetching all shops");
        return shopRepo.findAll();
    }
}
