package com.cac.portfolio.service;

import com.cac.portfolio.domain.Shop;

import java.util.List;

public interface ShopService {
    Shop saveShop (Shop shop);
    void addClientToShop (String clientName, String shopName);
    void addDeliverMenToShop (String deliverName, String shopName);
    void addCatalogueToShop (String catalogueName, String shopName);
    Shop getShop (String shopName);
    List<Shop> getShops();
}
