package com.cac.portfolio.service;

import com.cac.portfolio.domain.Client;

import java.util.List;

public interface ClientService {
    Client saveClient (Client client);
    Client getClient (String clientName);
    List<Client> getClients();
}
