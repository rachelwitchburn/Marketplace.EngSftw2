package com.marketplace.service;

import com.marketplace.model.Product;
import com.marketplace.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository useProductRepository;

    public Product saveProduct(Product product) {
        return useProductRepository.save(product);
    }
    
    public List<Product> listProducts() {
        return useProductRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return useProductRepository.findById(id);
    }

    public Product updateProduct(Long id, Product productUpdated){
        if (useProductRepository.existsById(id)) {
            productUpdated.setId(id);
            return useProductRepository.save(productUpdated);
        }

        return null;
    }

    public void deleteProduct(Long id) {
        useProductRepository.deleteById(id);
    }
}