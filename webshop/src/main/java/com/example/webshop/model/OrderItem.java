package com.example.webshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal priceAtPurchase;
}









// package com.example.webshop.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.math.BigDecimal;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// @Entity
// public class OrderItem {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long productId;
//     private Integer quantity;
//     private BigDecimal priceAtPurchase;

//     @ManyToOne
//     @JoinColumn(name = "order_id")
//     private OrderEntity order;

//     // Lombok will automatically generate constructors, getters, setters, and builder.
// }











// package com.example.webshop.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.math.BigDecimal;

// @Entity(name = "OrderItemModel")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class OrderItem {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long productId;
//     private String productName; // ✅ Add this
//     private Integer quantity;
//     private BigDecimal price; // Current product price (optional)
//     private BigDecimal priceAtPurchase; // Snapshot of price at time of order

    
//     // Custom constructor (without `id`) for creating order items manually
//     public OrderItem(Long productId, Integer quantity, BigDecimal price, BigDecimal priceAtPurchase) {
//         this.productId = productId;
//         this.quantity = quantity;
//         this.price = 0.0; // Default, or you can pass as parameter if needed
//         this.priceAtPurchase = priceAtPurchase;
//     }
    
// }
