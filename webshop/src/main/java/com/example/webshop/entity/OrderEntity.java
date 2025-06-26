package com.example.webshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.webshop.entity.OrderEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private String address;

    // getter and setter for address
    public String getAddress() {
        return address;
    }   
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private CustomerEntity customer;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")  // foreign key in order_items table
    private List<OrderItemEntity> items;

    @Column(nullable = false)
    private String status;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public void recalculateTotalPrice() {
        if (items == null || items.isEmpty()) {
            this.totalPrice = BigDecimal.ZERO;
        } else {
            this.totalPrice = items.stream()
                    .map(item -> item.getPriceAtPurchase()
                                     .multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }
}


