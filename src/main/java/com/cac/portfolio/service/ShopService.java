package com.cac.portfolio.service;

import com.cac.portfolio.domain.Shop;

import java.util.List;

public interface ShopService {
    Shop saveShop (Shop shop);
    Boolean addClientToShop (String clientName, String shopName);
    Boolean addDeliverMenToShop (String deliverName, String shopName);
    Boolean addCatalogueToShop (String catalogueName, String shopName);
    Shop getShop (String shopName);
    List<Shop> getShops();
}
