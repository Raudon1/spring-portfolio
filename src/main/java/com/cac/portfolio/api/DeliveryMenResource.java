package com.cac.portfolio.api;

import com.cac.portfolio.domain.DeliveryMan;
import com.cac.portfolio.service.DeliveryManService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeliveryMenResource {
    private final DeliveryManService deliveryManService;

    @GetMapping("/deliveryman")
    public ResponseEntity<List<DeliveryMan>> getDeliveryMan() {
        return ResponseEntity.ok().body(deliveryManService.getDelivery());
    }

    @PostMapping("/deliveryman/save")
    public ResponseEntity<DeliveryMan> saveClient(@RequestBody DeliveryMan deliveryMan) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/deliveryman/save").toUriString());
        return ResponseEntity.created(null).body(deliveryManService.saveDelivery(deliveryMan));
    }

}
