package com.example.webshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;           // Current product price
    private BigDecimal priceAtPurchase; // Price at time of order (important for history)
}









// File: OrderItemDTO.java
// package com.example.webshop.dto;

// import lombok.Data;
// import java.math.BigDecimal;

// @Data
// public class OrderItemDto {
//     private Long productId;
//     private Integer quantity;
//     private BigDecimal price;
//     private BigDecimal priceAtPurchase;

//     // Getters and setters
//     public Long getProductId() { return productId; }
//     public void setProductId(Long productId) { this.productId = productId; }
//     public Integer getQuantity() { return quantity; }
//     public void setQuantity(Integer quantity) { this.quantity = quantity; }
//     public BigDecimal getPriceAtPurchase() { return priceAtPurchase; }
//     public void setPriceAtPurchase(BigDecimal priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }
//     public BigDecimal getPrice() { return price; }
//     public void setPrice(BigDecimal price) { this.price = price; }  
    
//     public OrderItemDto(Long productId, Integer quantity, BigDecimal price, BigDecimal priceAtPurchase) {
//         this.productId = productId;
//         this.quantity = quantity;
//         this.price = price;
//         this.priceAtPurchase = priceAtPurchase;
//     }
// }












// package com.example.webshop.dto;

// import lombok.Data;
// import java.math.BigDecimal;
// import com.example.webshop.dto.OrderItemDTO; // ✅ Correct


// @Data
// public class OrderItemDTO {
//     private Long productId;
//     private Integer quantity;
//     private BigDecimal price;
//     private BigDecimal priceAtPurchase;
    
//     // Getters and setters
//     public Long getProductId() { return productId; }
//     public void setProductId(Long productId) { this.productId = productId; }
//     public Integer getQuantity() { return quantity; }
//     public void setQuantity(Integer quantity) { this.quantity = quantity; }
//     public BigDecimal getPriceAtPurchase() { return priceAtPurchase; }
//     public void setPriceAtPurchase(BigDecimal priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }
//     public BigDecimal getPrice() { return price; }
//     public void setPrice(BigDecimal price) { this.price = price; }


//     // Setters constructor(s)

//     public OrderItemDTO(Long productId, Integer quantity, BigDecimal price, BigDecimal priceAtPurchase) {
//         this.productId = productId;
//         this.quantity = quantity;
//         this.price = price;
//         this.priceAtPurchase = priceAtPurchase;
//     }

// }
