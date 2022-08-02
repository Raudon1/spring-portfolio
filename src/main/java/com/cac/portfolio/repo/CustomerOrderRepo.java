package com.cac.portfolio.repo;

import com.cac.portfolio.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Long> {
    CustomerOrder findByOrderNumber(String orderNumber);
}