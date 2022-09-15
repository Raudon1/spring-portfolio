package com.cac.portfolio.service;

import com.cac.portfolio.domain.CustomerOrder;
import com.cac.portfolio.domain.DeliveryMan;
import com.cac.portfolio.repo.CustomerOrderRepo;
import com.cac.portfolio.repo.DeliveryMRepo;
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
class DeliveryManServiceImplTest {
    @Mock
    private DeliveryMRepo deliveryMRepo;

    @Mock
    private CustomerOrderRepo customerOrderRepo;

    @InjectMocks
    private DeliveryManServiceImpl deliveryManServiceImpl;

    @Test
    void test_saveDelivery() {
        //Given
        String expected = "testD";
        DeliveryMan deliveryMan = new DeliveryMan();
        deliveryMan.setName(expected);
        when(deliveryMRepo.save(deliveryMan)).thenReturn(deliveryMan);
        //When
        DeliveryMan obtainedDeliveryMan = deliveryManServiceImpl.saveDelivery(deliveryMan);
        //Then
        Assertions.assertThat(obtainedDeliveryMan.getName()).isEqualTo(expected);
    }

    @Test
    void test_addOrderToDeli() {
        //Given
        String expectedDeli = "testD";
        String expectedOrder = "1111";
        DeliveryMan deliveryMan = Mockito.mock(DeliveryMan.class);
        CustomerOrder customerOrder = new CustomerOrder();
        Collection<CustomerOrder> customerOrderList = new ArrayList<>();
        when(deliveryMan.getCustomerOrders()).thenReturn(customerOrderList);
        when(deliveryMRepo.findByName(expectedDeli)).thenReturn(deliveryMan);
        when(customerOrderRepo.findByOrderNumber(expectedOrder)).thenReturn(customerOrder);
        //When
         Boolean test = deliveryManServiceImpl.addOrderToDeli(expectedOrder,expectedDeli);
        //Then
        Assertions.assertThat(test).isTrue();
    }

    @Test
    void test_getDelivery() {
        //Given
        String expected = "testDeli";
        DeliveryMan deliveryMan = new DeliveryMan();
        deliveryMan.setName(expected);
        when(deliveryMRepo.findByName(expected)).thenReturn(deliveryMan);
        //When
        DeliveryMan obtainedDeliveryMan = deliveryManServiceImpl.getDelivery(expected);
        //Then
        Assertions.assertThat(obtainedDeliveryMan.getName()).isEqualTo(expected);
    }

    @Test
    void test_GetDeliveries() {
        //Given
        List<DeliveryMan> deliveryManList = new ArrayList<>();
        when(deliveryMRepo.findAll()).thenReturn(deliveryManList);
        //When
        List<DeliveryMan> obtainedDeliveryMan = deliveryManServiceImpl.getDeliveries();
        //Then
        Assertions.assertThat(obtainedDeliveryMan).isNotNull();
    }
}