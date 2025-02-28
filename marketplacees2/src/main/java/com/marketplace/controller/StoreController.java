package com.marketplace.controller;

import com.marketplace.model.Store;
import com.marketplace.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/stores")
public class StoreController {
    @Autowired
    private StoreService useStoreService;

    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return useStoreService.saveStore(store);
    }

    @GetMapping
    public List<Store> listStores() {
        return useStoreService.listStores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Optional<Store> store = useStoreService.getStoreById(id);
        return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody Store store) {
        Store updatedStore = useStoreService.updateStore(id, store);
        return (updatedStore != null) ? ResponseEntity.ok(updatedStore) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        useStoreService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}
