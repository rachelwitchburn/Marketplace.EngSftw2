package com.marketplace.repository;

import com.marketplace.model.Buyer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerRepository {
    private static final String FILE_PATH = "buyers.ser";
    
    public List<Buyer> loadBuyers() {
        List<Buyer> buyers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            buyers = (List<Buyer>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return buyers;
    }
    
    public void saveBuyers(List<Buyer> buyers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(buyers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addBuyer(Buyer buyer) {
        List<Buyer> buyers = loadBuyers();
        int maxId = 0;
        for (Buyer b : buyers) {
            if (b.getId() > maxId) {
                maxId = b.getId();
            }
        }
        buyer.setId(maxId + 1);
        buyers.add(buyer);
        saveBuyers(buyers);
    }
    
    public List<Buyer> getAllBuyers() {
        return loadBuyers();
    }
    
    public boolean updateBuyer(Buyer updatedBuyer) {
        List<Buyer> buyers = loadBuyers();
        boolean found = false;
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i).getId() == updatedBuyer.getId()) {
                buyers.set(i, updatedBuyer);
                found = true;
                break;
            }
        }
        if (found) {
            saveBuyers(buyers);
        }
        return found;
    }
    
    public boolean removeBuyer(int id) {
        List<Buyer> buyers = loadBuyers();
        boolean removed = buyers.removeIf(b -> b.getId() == id);
        if (removed) {
            saveBuyers(buyers);
        }
        return removed;
    }
}
