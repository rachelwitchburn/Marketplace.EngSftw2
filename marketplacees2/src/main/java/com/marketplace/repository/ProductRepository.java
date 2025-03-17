package com.marketplace.repository;

import com.marketplace.model.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final String FILE_PATH = "products.ser";
    
    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            products = (List<Product>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    public void saveProducts(List<Product> products) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addProduct(Product product) {
        List<Product> products = loadProducts();
        int maxId = 0;
        for (Product p : products) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        product.setId(maxId + 1);
        products.add(product);
        saveProducts(products);
    }
    
    public List<Product> getAllProducts() {
        return loadProducts();
    }
    
    public boolean updateProduct(Product updatedProduct) {
        List<Product> products = loadProducts();
        boolean found = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == updatedProduct.getId()) {
                products.set(i, updatedProduct);
                found = true;
                break;
            }
        }
        if (found) {
            saveProducts(products);
        }
        return found;
    }
    
    public boolean removeProduct(int id) {
        List<Product> products = loadProducts();
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (removed) {
            saveProducts(products);
        }
        return removed;
    }
}
