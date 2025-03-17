package com.marketplace.service;

import com.marketplace.model.Buyer;
import com.marketplace.repository.BuyerRepository;
import java.util.List;

public class BuyerService {
    private BuyerRepository repository = new BuyerRepository();
    
    public void addBuyer(Buyer buyer) {
        repository.addBuyer(buyer);
    }
    
    public List<Buyer> listBuyers() {
        return repository.getAllBuyers();
    }
    
    public boolean updateBuyer(Buyer buyer) {
        return repository.updateBuyer(buyer);
    }
    
    public boolean removeBuyer(int id) {
        return repository.removeBuyer(id);
    }
}
