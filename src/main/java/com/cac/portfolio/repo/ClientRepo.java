package com.cac.portfolio.repo;

import com.cac.portfolio.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findByName(String clientName);
}
