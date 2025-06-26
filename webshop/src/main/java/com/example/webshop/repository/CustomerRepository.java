// src/main/java/com/example/webshop/repository/CustomerRepository.java
package com.example.webshop.repository;

import com.example.webshop.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByEmail(String email);
}
