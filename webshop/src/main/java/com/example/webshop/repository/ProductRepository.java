package com.example.webshop.repository;

import com.example.webshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository already provides:
    // - save(Product entity)
    // - findById(Long id)
    // - findAll()
    // - deleteById(Long id)
    // - and many other useful methods

    // You can add custom queries here if needed
}
