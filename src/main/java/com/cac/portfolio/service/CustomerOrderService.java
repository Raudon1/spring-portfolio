package com.cac.portfolio.service;

import com.cac.portfolio.domain.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {
    CustomerOrder saveOrder(CustomerOrder customerOrder);
    Boolean addProductsToOrder (String productsName, String orderName);
    CustomerOrder getOrder (String orderName);
    List<CustomerOrder> getOrders();
}
