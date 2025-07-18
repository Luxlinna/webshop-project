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


