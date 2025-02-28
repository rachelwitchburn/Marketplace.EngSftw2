package com.marketplace.controller;

import com.marketplace.model.Buyer;
import com.marketplace.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/buyers")
public class BuyerController {
    @Autowired
    private BuyerService useBuyerService;

    @PostMapping
    public Buyer createBuyer(@RequestBody Buyer buyer) {
        return useBuyerService.saveBuyer(buyer);
    }

    @GetMapping
    public List<Buyer> listBuyer() {
        return useBuyerService.listBuyers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        Optional<Buyer> buyer = useBuyerService.getBuyerById(id);
        return buyer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buyer> updateBuyer(@PathVariable Long id, @RequestBody Buyer buyer) {
        Buyer updatedBuyer = useBuyerService.updateBuyer(id, buyer);
        return (updatedBuyer != null) ? ResponseEntity.ok(updatedBuyer) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        useBuyerService.deleteBuyer(id);
        return ResponseEntity.noContent().build();
    }
}
