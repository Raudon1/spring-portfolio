package com.cac.portfolio.api;

import com.cac.portfolio.domain.Client;
import com.cac.portfolio.service.ClientService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientResource {
    private final ClientService clientService;

    @GetMapping("/client")
    public ResponseEntity<List<Client>> getClient() {
        return ResponseEntity.ok().body(clientService.getClients());
    }

    @PostMapping("/client/save")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/client/save").toUriString());
        return ResponseEntity.created(null).body(clientService.saveClient(client));
    }

    @PostMapping("/role/adddordertoclient")
    public ResponseEntity<?> addOrderToClient(@RequestBody OrderToClientForm form) {
        clientService.addOrderToClient(form.getClientName(),form.getOrderNumber());
        return ResponseEntity.ok().build();
    }

    @Data
    class OrderToClientForm {
        private String clientName;
        private String orderNumber;
    }
}

