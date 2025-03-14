package com.marketplace.service;

import com.marketplace.model.Store;
import com.marketplace.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository useStoreRepository;

    public Store saveStore(Store store) {
        return useStoreRepository.save(store);
    }
    
    public List<Store> listStores() {
        return useStoreRepository.findAll();
    }

    public Optional<Store> getStoreById(Long id){
        return useStoreRepository.findById(id);
    }

    public Store updateStore(Long id, Store storeUpdated){
        if (useStoreRepository.existsById(id)) {
            storeUpdated.setId(id);
            return useStoreRepository.save(storeUpdated);
        }

        return null;
    }

    public void deleteStore(Long id) {
        useStoreRepository.deleteById(id);
    }
}
