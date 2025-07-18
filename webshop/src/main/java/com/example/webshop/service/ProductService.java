package com.example.webshop.service;

import com.example.webshop.exception.ProductNotFoundException;
import com.example.webshop.model.Product;
import com.example.webshop.repository.ProductRepository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.example.webshop.service.OrderService;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final OrderService orderService;

    public ProductService(ProductRepository productRepo, OrderService orderService) {
        this.productRepo = productRepo;
        this.orderService = orderService;
    }

    // Retrieve all products
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // Retrieve a product by its ID, throw exception if not found
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product ID " + id + " not found"));
    }

    // Example method to use orderService (can be implemented as needed)
    public void doSomethingWithOrders() {
        // Example: orderService.placeOrder(...);
    }
}


