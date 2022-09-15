package com.cac.portfolio.service;

import com.cac.portfolio.domain.CustomerOrder;
import com.cac.portfolio.domain.DeliveryMan;
import com.cac.portfolio.repo.CustomerOrderRepo;
import com.cac.portfolio.repo.DeliveryMRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DeliveryManServiceImpl implements DeliveryManService{
    private final DeliveryMRepo deliveryMRepo;
    private final CustomerOrderRepo customerOrderRepo;

    @Override
    public DeliveryMan saveDelivery(DeliveryMan deliveryMan) {
        log.info("Saving new delivery man {} to the database", deliveryMan.getName());
        return deliveryMRepo.save(deliveryMan);
    }

    @Override
    public Boolean addOrderToDeli(String orderNumber, String deliverName) {
        log.info("Adding order number {} to deliveryMan {}", orderNumber, deliverName);
        DeliveryMan deliveryMan = deliveryMRepo.findByName(deliverName);
        CustomerOrder customerOrder = customerOrderRepo.findByOrderNumber(orderNumber);
        return deliveryMan.getCustomerOrders().add(customerOrder);
    }

    @Override
    public DeliveryMan getDelivery(String deliveryName) {
        log.info("Fetching delivering man {}", deliveryName);
        return deliveryMRepo.findByName(deliveryName);
    }

    @Override
    public List<DeliveryMan> getDeliveries() {
        log.info("Fetching all delivering men");
        return deliveryMRepo.findAll();
    }
}
