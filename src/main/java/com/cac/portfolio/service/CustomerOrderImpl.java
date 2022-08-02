package com.cac.portfolio.service;

import com.cac.portfolio.domain.CustomerOrder;
import com.cac.portfolio.domain.Products;
import com.cac.portfolio.repo.CustomerOrderRepo;
import com.cac.portfolio.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerOrderImpl implements CustomerOrderService {
    private final CustomerOrderRepo customerOrderRepo;
    private final ProductRepo productRepo;

    @Override
    public CustomerOrder saveOrder(CustomerOrder order) {
        log.info("Saving new order {} to the database", order.getOrderNumber());
        return customerOrderRepo.save(order);
    }

    @Override
    public void addProductsToOrder(String productsName, String orderNumber) {
        log.info("Adding products {} to order {}", productsName, orderNumber );
        CustomerOrder customerOrder = customerOrderRepo.findByOrderNumber(orderNumber);
        Products products = productRepo.findByName(productsName);
        customerOrder.getProducts().add(products);
    }

    @Override
    public CustomerOrder getOrder(String orderName) {
        log.info("Fetching orders {}", orderName);
        return customerOrderRepo.findByOrderNumber(orderName);
    }

    @Override
    public List<CustomerOrder> getOrders() {
        log.info("Fetching all orders");
        return customerOrderRepo.findAll();
    }
}
