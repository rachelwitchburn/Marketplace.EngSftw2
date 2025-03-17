package com.marketplace.repository;

import com.marketplace.model.Store;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StoreRepository {
    private static final String FILE_PATH = "stores.ser";
    
    public List<Store> loadStores() {
        List<Store> stores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            stores = (List<Store>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stores;
    }
    
    public void saveStores(List<Store> stores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(stores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addStore(Store store) {
        List<Store> stores = loadStores();
        int maxId = 0;
        for (Store l : stores) {
            if (l.getId() > maxId) {
                maxId = l.getId();
            }
        }
        store.setId(maxId + 1);
        stores.add(store);
        saveStores(stores);
    }
    
    public List<Store> getAllStores() {
        return loadStores();
    }
    
    public boolean updateStore(Store updatedStore) {
        List<Store> stores = loadStores();
        boolean found = false;
        for (int i = 0; i < stores.size(); i++) {
            if (stores.get(i).getId() == updatedStore.getId()) {
                stores.set(i, updatedStore);
                found = true;
                break;
            }
        }
        if (found) {
            saveStores(stores);
        }
        return found;
    }
    
    public boolean removeStore(int id) {
        List<Store> stores = loadStores();
        boolean removed = stores.removeIf(l -> l.getId() == id);
        if (removed) {
            saveStores(stores);
        }
        return removed;
    }
}
