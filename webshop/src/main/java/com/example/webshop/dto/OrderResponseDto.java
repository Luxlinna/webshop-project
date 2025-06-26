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









// package com.example.webshop.dto;

// import lombok.Data;
// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.List;

// @Data
// public class OrderResponseDto {
//     private Long id;
//     private CustomerInfoDto customer;
//     private List<OrderItemDto> items;
//     private BigDecimal total;
//     private String status;
//     private LocalDateTime orderDate;
// }










// package com.example.webshop.dto;

// public class OrderResponseDTO {
//     private Long orderId;
//     private String message;

//     public OrderResponseDTO(Long orderId, String message) {
//         this.orderId = orderId;
//         this.message = message;
//     }

//     // Getters and setters
//     public void setOrderId(Long orderId) {
//         this.orderId = orderId;
//     }
//     public void setMessage(String message) {
//         this.message = message;
//     }

//     // public Long getOrderId() {
//     //     return orderId;
//     // }

//     // public String getMessage() {
//     //     return message;
//     // }
// }






// package com.example.webshop.dto;

// import lombok.AllArgsConstructor;
// import lombok.Data;

// @Data
// @AllArgsConstructor
// public class OrderResponseDTO {
//     private String orderId;
//     private String message;
// }
