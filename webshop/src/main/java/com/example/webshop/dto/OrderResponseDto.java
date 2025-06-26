package com.example.webshop.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;                        // Order ID
    private CustomerInfoDto customer;      // Customer info
    private List<OrderItemDto> items;      // Ordered items
    private BigDecimal total;              // Total order price
    private String status;                 // Order status (e.g., NEW, SHIPPED)
    private LocalDateTime orderDate;       // Date of order
}


