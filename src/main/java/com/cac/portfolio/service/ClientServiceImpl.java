package com.cac.portfolio.service;

import com.cac.portfolio.domain.Client;
import com.cac.portfolio.repo.ClientRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepo clientRepo;


    @Override
    public Client saveClient(Client client) {
        log.info("Saving new client {} to the database", client.getName());
        return clientRepo.save(client);
    }

    @Override
    public Client getClient(String clientName) {
        log.info("Fetching client {}", clientName);
        return clientRepo.findByName(clientName);
    }

    @Override
    public List<Client> getClients() {
        log.info("Fetching all clients");
        return clientRepo.findAll();
    }
}
