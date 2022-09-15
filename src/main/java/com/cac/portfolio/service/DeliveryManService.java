package com.cac.portfolio.service;

import com.cac.portfolio.domain.DeliveryMan;

import java.util.List;

public interface DeliveryManService {
    DeliveryMan saveDelivery (DeliveryMan deliveryMan);
    Boolean addOrderToDeli (String orderNumber, String deliveryName);
    DeliveryMan getDelivery(String deliveryName);
    List<DeliveryMan> getDeliveries();
}
