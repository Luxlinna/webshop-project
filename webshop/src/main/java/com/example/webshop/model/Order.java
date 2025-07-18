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

