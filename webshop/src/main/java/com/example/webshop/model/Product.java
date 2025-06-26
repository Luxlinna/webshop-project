package com.example.webshop.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Integer quantity;  // ✅ This field must be here
    private Integer stock;
    
    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Additional field for stock management
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    // Lombok's @Data adds getQuantity() and setQuantity()
}









// package com.example.webshop.model;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import java.math.BigDecimal;

// @NoArgsConstructor 
// @Data
// @AllArgsConstructor
// public class Product {
//     private Long id;
//     private String name;
//     private String description;
//     private Double price;
//     private String imageUrl;
//     private int stock;
// }









// package com.example.webshop.model;

// import lombok.Data;
// import lombok.AllArgsConstructor;
// import lombok.NoArgsConstructor;

// import java.math.BigDecimal;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class Product {
//     private Long id;
//     private String name;
//     private String description;
//     private BigDecimal price;
//     private String imageUrl;
//     private int stock;
// }
