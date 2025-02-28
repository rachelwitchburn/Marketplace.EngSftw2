package com.marketplace.service;

import com.marketplace.model.Buyer;
import com.marketplace.repository.BuyerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository useBuyerRepository;

    public Buyer saveBuyer(Buyer buyer) {
        return useBuyerRepository.save(buyer);
    }
    
    public List<Buyer> listBuyers() {
        return useBuyerRepository.findAll();
    }

    public Optional<Buyer> getBuyerById(Long id){
        return useBuyerRepository.findById(id);
    }

    public Buyer updateBuyer(Long id, Buyer buyerUpdated){
        if (useBuyerRepository.existsById(id)) {
            buyerUpdated.setId(id);
            return useBuyerRepository.save(buyerUpdated);
        }

        return null;
    }

    public void deleteBuyer(Long id) {
        useBuyerRepository.deleteById(id);
    }
}