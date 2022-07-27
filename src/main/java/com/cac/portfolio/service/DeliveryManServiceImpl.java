package com.cac.portfolio.service;

import com.cac.portfolio.domain.DeliveryMan;
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

    @Override
    public DeliveryMan saveDelivery(DeliveryMan deliveryMan) {
        log.info("Saving new delivery man {} to the database", deliveryMan.getName());
        return deliveryMRepo.save(deliveryMan);
    }

    @Override
    public DeliveryMan getDelivery(String deliveryName) {
        log.info("Fetching delivering man {}", deliveryName);
        return deliveryMRepo.findByName(deliveryName);
    }

    @Override
    public List<DeliveryMan> getDelivery() {
        log.info("Fetching all delivering men");
        return deliveryMRepo.findAll();
    }
}
