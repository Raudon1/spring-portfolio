package com.cac.portfolio.api;

import com.cac.portfolio.domain.Shop;
import com.cac.portfolio.service.ShopService;
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
public class ShopResource {
    private final ShopService shopService;

    @GetMapping("/shop")
    public ResponseEntity<List<Shop>> getClient() {
        return ResponseEntity.ok().body(shopService.getShops());
    }

    @PostMapping("/shop/save")
    public ResponseEntity<Shop> saveClient(@RequestBody Shop shop) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/shop/save").toUriString());
        return ResponseEntity.created(null).body(shopService.saveShop(shop));
    }

    @PostMapping("/role/addclienttoshop")
    public ResponseEntity<?> addClientToShop(@RequestBody ClientToShopForm form) {
        shopService.addClientToShop(form.getShopName(), form.getClientName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/role/adddelitoshop")
    public ResponseEntity<?> addDeliverToShop(@RequestBody DeliverToShopForm form) {
        shopService.addDeliverMenToShop(form.getShopName(), form.getDeliverName());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/role/addcataloguetoshop")
    public ResponseEntity<?> addCatalogueToShop(@RequestBody CatalogueToShopForm form) {
        shopService.addCatalogueToShop(form.getShopName(), form.getCatalogueName());
        return ResponseEntity.ok().build();
    }


    @Data
    class ClientToShopForm {
        private String shopName;
        private String clientName;

    }

    @Data
    class DeliverToShopForm {
        private String shopName;
        private String deliverName;

    }

    @Data
    class CatalogueToShopForm {
        private String shopName;
        private String catalogueName;

    }
}
