package com.cac.portfolio.api;

import com.cac.portfolio.domain.CustomerOrder;
import com.cac.portfolio.service.CustomerOrderService;
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
public class CustomerOrderResource {
    private final CustomerOrderService customerOrderService;

    @GetMapping("/order")
    public ResponseEntity<List<CustomerOrder>> getOrders() {
        return ResponseEntity.ok().body(customerOrderService.getOrders());
    }

    @PostMapping("/order/save")
    public ResponseEntity<CustomerOrder> saveOrder(@RequestBody CustomerOrder customerOrder) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/order/save").toUriString());
        return ResponseEntity.created(null).body(customerOrderService.saveOrder(customerOrder));
    }

    @PostMapping("/order/addproducttoorder")
    public ResponseEntity<?> addProductToOrder(@RequestBody ProductToOrderForm form) {
        customerOrderService.addProductsToOrder(form.getOrderNumber(), form.getProductName());
        return ResponseEntity.ok().build();
    }

    @Data
    class ProductToOrderForm {
        private String orderNumber;
        private String productName;
    }
}
