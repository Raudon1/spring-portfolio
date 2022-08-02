package com.cac.portfolio.service;

import com.cac.portfolio.domain.Client;

import java.util.List;

public interface ClientService {
    Client saveClient (Client client);
    void addOrderToClient (String orderNumber, String clientName);
    Client getClient (String clientName);
    List<Client> getClients();
}
