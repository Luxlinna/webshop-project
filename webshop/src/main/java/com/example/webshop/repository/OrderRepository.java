package com.example.webshop.repository;

import com.example.webshop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // ✅ Find by customer email
    List<OrderEntity> findByCustomer_Email(String email);

    // ✅ Find by customer id
    List<OrderEntity> findByCustomer_Id(Long customerId);
    
    // ✅ Find by order status
    List<OrderEntity> findByStatus(String status);

}

