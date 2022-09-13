package com.cac.portfolio.service;

import com.cac.portfolio.domain.CustomerOrder;
import com.cac.portfolio.domain.Products;
import com.cac.portfolio.repo.CustomerOrderRepo;
import com.cac.portfolio.repo.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerOrderImplTest {
    @Mock
    private CustomerOrderRepo customerOrderRepo;

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private CustomerOrderImpl customerOrderImpl;

    @Test
    void saveOrder() {
        //Given
        String expected = "1111";
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderNumber(expected);
        when(customerOrderRepo.save(customerOrder)).thenReturn(customerOrder);
        //When
        CustomerOrder ObtainedCustomerOrder = customerOrderImpl.saveOrder(customerOrder);
        //Then
        Assertions.assertThat(ObtainedCustomerOrder.getOrderNumber()).isEqualTo(expected);
    }

    @Test
    void addProductsToOrder() {
        //Given
        String customerOrderExpected = "1111";
        String productExpected = "TestP";
        CustomerOrder customerOrder = Mockito.mock(CustomerOrder.class);
        Products products = new Products();
        Collection<Products> productsList = new ArrayList<>();
        when(customerOrder.getProducts()).thenReturn(productsList);
        when(customerOrderRepo.findByOrderNumber(customerOrderExpected)).thenReturn(customerOrder);
        when(productRepo.findByName(productExpected)).thenReturn(products);
        //When
        Boolean test = customerOrderImpl.addProductsToOrder(productExpected,customerOrderExpected);
        //Then
        Assertions.assertThat(test).isTrue();
    }

    @Test
    void getOrder() {
        //Given
        String expected = "1111";
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderNumber(expected);
        when(customerOrderRepo.findByOrderNumber(expected)).thenReturn(customerOrder);
        //When
        CustomerOrder obtainedCustomerOrder = customerOrderImpl.getOrder(expected);
        //Then
        Assertions.assertThat(obtainedCustomerOrder.getOrderNumber()).isEqualTo(expected);
    }

    @Test
    void getOrders() {
        //Given
        List<CustomerOrder> customerOrderList = new ArrayList<>();
        when(customerOrderRepo.findAll()).thenReturn(customerOrderList);
        //When
        List<CustomerOrder> customerOrderLists = customerOrderImpl.getOrders();
        //Then
        Assertions.assertThat(customerOrderLists).isNotNull();
    }
}