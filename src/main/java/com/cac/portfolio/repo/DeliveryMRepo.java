package com.cac.portfolio.repo;

import com.cac.portfolio.domain.DeliveryMan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryMRepo extends JpaRepository<DeliveryMan, Long> {
    DeliveryMan findByName(String deliName);
}
