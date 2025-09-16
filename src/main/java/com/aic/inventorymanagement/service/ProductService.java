package com.aic.inventorymanagement.service;

import com.aic.inventorymanagement.model.Product;
import com.aic.inventorymanagement.dao.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public void updateProduct(Product product) {
        repository.update(product);
    }

    public void deleteProduct(int id) {
        repository.delete(id);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id);
    }
}
