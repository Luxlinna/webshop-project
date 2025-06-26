// com.example.webshop.model.Order.java
package com.example.webshop.model;

import java.math.BigDecimal;
import com.example.webshop.model.Order;


public class Order {
    private Long id;
    private String customerName;
    private BigDecimal totalAmount;

    public Order(Long id, String customerName, BigDecimal totalAmount) {
        this.id = id;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters  
    public Long getId() {
        return id;
    }   
    public void setId(Long id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount; 
    }

}









// package com.example.webshop.model;

// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.List;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Order {
//     private Long orderId;
//     private String customer;
//     private String address;
//     private String email;
//     private LocalDateTime orderDate;
//     private List<OrderItem> items;

//     public BigDecimal getTotalPrice() {
//         if (items == null || items.isEmpty()) {
//             return BigDecimal.ZERO;
//         }
//         return items.stream()
//                 .map(item -> item.getPriceAtPurchase()
//                         .multiply(BigDecimal.valueOf(item.getQuantity())))
//                 .reduce(BigDecimal.ZERO, BigDecimal::add);
//     }
// }










// package com.example.webshop.model;

// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.List;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Order {
//     private Long OrderId;
//     private String customer;  // or a Customer DTO if you have one
//     private String address;
//     private String email;
//     private LocalDateTime orderDate;
//     private List<OrderItem> items;
    

//     public BigDecimal getTotalPrice() {
//         if (items == null || items.isEmpty()) {
//             return BigDecimal.ZERO;
//         }
//         return items.stream()
//                 .map(item -> item.getPriceAtPurchase().multiply(BigDecimal.valueOf(item.getQuantity())))
//                 .reduce(BigDecimal.ZERO, BigDecimal::add);
//     }

//     // Other methods...
//     public BigDecimal getTotalAmount() {
//         if (items == null || items.isEmpty()) {
//             return BigDecimal.ZERO;
//         }
//         return items.stream()
//                 .map(item -> item.getPriceAtPurchase().multiply(BigDecimal.valueOf(item.getQuantity())))
//                 .reduce(BigDecimal.ZERO, BigDecimal::add);
//     }

// }

