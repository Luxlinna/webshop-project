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










// package com.example.webshop.repository;

// import com.example.webshop.model.Order;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
//     // Query by nested field in @Embedded CustomerInfo
//     List<OrderEntity> findByCustomerInfo_Email(String email);

//     // Assuming status is a direct field in Order
//     List<OrderEntity> findByCustomerId(Long customerId);
//     List<OrderEntity> findByStatus(String status);


// }










// package com.example.webshop.repository;

// import com.example.webshop.model.Order;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;  // <-- Import List

// public interface OrderRepository extends JpaRepository<Order, Long> {
//     List<Order> findByCustomerId(Long customerId);
//     List<Order> findByStatus(String status);
// }
