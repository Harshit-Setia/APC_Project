package com.aic.inventorymanagement.controller;

import com.aic.inventorymanagement.model.Product;
import com.aic.inventorymanagement.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public String add(@RequestBody Product product) {
        service.addProduct(product);
        return "Product added successfully!";
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @PutMapping("/{id}")
public String update(@PathVariable int id, @RequestBody Product product) {
    product.setId(id);   // set the path id into the product
    service.updateProduct(product);
    return "Product updated successfully!";
}



    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteProduct(id);
        return "Product deleted successfully!";
    }
}
