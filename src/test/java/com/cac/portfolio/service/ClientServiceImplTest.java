package com.cac.portfolio.service;

import com.cac.portfolio.domain.Client;
import com.cac.portfolio.domain.CustomerOrder;
import com.cac.portfolio.repo.ClientRepo;
import com.cac.portfolio.repo.CustomerOrderRepo;
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
class ClientServiceImplTest {
    @Mock
    private ClientRepo clientRepo;

    @Mock
    private CustomerOrderRepo customerOrderRepo;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @Test
    void test_saveClient() {
        //Given
        String expected = "TestCli";
        Client client = new Client();
        client.setName(expected);
        when(clientRepo.save(client)).thenReturn(client);
        //When
        Client obtainedClient = clientServiceImpl.saveClient(client);
        //Then
        Assertions.assertThat(obtainedClient.getName()).isEqualTo(expected);
    }

    @Test
    void test_addOrderToClient() {
        //Given
        String clientExpected = "TestCli";
        String cOrderExpected = "123456";
        Client client = Mockito.mock(Client.class);
        CustomerOrder customerOrder = new CustomerOrder();
        Collection<CustomerOrder> cOrderCollection = new ArrayList<>();
        when(client.getCustomerOrders()).thenReturn(cOrderCollection);
        when(clientRepo.findByName(clientExpected)).thenReturn(client);
        when(customerOrderRepo.findByOrderNumber(cOrderExpected)).thenReturn(customerOrder);
        //When
        Boolean test = clientServiceImpl.addOrderToClient(cOrderExpected,clientExpected);
        //Then
        Assertions.assertThat(test).isTrue();
    }

    @Test
    void test_getClient() {
        //Given
        String expected = "TestCli";
        Client client = new Client();
        client.setName(expected);
        when(clientRepo.findByName(expected)).thenReturn(client);
        //When
        Client obtainedClient = clientServiceImpl.getClient(expected);
        //Then
        Assertions.assertThat(obtainedClient.getName()).isEqualTo(expected);
    }

    @Test
    void test_getClients() {
        //Given
        List<Client> clientList = new ArrayList<>();
        when(clientRepo.findAll()).thenReturn(clientList);
        //When
        List<Client> clients = clientServiceImpl.getClients();
        //Then
        Assertions.assertThat(clients).isNotNull();
    }
}