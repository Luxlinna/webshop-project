package com.example.webshop.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long orderId;
    private String status;
    private String customer;
    private List<OrderItemDto> items;
    private BigDecimal total;

    public BigDecimal getTotalPrice() {
        return items.stream()
            .map(item -> item.getPriceAtPurchase().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + orderId +
                ", status='" + status + '\'' +
                ", customer='" + customer + '\'' +
                ", items=" + items +
                ", total=" + total +
                '}';
    }
}

