package com.example.webshop.model;

import lombok.Data;

import java.util.List;

@Data
public class CustomerInfo {

    private Long id;

    private String firstName;

    private String lastName;

    private String name;

    private String email;

    private String address;

    // If you want to expose order data, use an OrderDTO here
    private List<Order> orders;

    public CustomerInfo() {}

    public CustomerInfo(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerInfo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            ", orders=" + orders +
            '}';
    }
}









// package com.example.webshop.model;

// import lombok.Data;
// import java.util.List;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import com.example.webshop.model.OrderEntity;


// @Data
// @Entity
// public class CustomerInfo {


//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String firstName;
//     private String lastName;
//     private String name;
//     private String email;
//     private String address;

//     private List<OrderEntity> orders;

//     // Temporarily remove these until classes exist
//     // private Customer customer;
//     // private List<Item> items;

//     public CustomerInfo() {}

//     public CustomerInfo(Long id, String name, String email, String address) {
//         this.id = id;
//         this.name = name;
//         this.email = email;
//         this.address = address;
//     }

//     @Override
//     public String toString() {
//         return "CustomerInfo{" +
//             "id=" + id +
//             ", name='" + name + '\'' +
//             ", email='" + email + '\'' +
//             ", address='" + address + '\'' +
//             ", orders=" + orders +
//             '}';
//     }
// }









// package com.example.webshop.model;

// import lombok.Data;

// import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;


// @Data
// @Entity

// public class CustomerInfo {
//     // @Id
//     // private Long id; // Unique identifier for the customer info
//     // private String name;
//     // private String email;
//     // private String address;

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String firstName;
//     private String lastName;
//     private String name; // Combined name field
//     private String email;
//     private String address;

//     private List<Order> orders;
//     private Customer customer;  // Or whatever type it should be
//     private List<Item> items;

//     public List<Order> getOrders() { return orders; }
//     public Customer getCustomer() { return customer; }
//     public List<Item> getItems() { return items; }

//     public CustomerInfo() {
//         // Default constructor
//     }

//     public CustomerInfo(Long id, String name, String email, String address) {
//         this.id = id;
//         this.name = name;
//         this.email = email;
//         this.address = address;
//     }
//     public Long getId() {
//         return id;
//     }
//     public void setId(Long id) {
//         this.id = id;
//     }
//     public String getName() {
//         return name;
//     }
//     public void setName(String name) {
//         this.name = name;
//     }
//     public String getEmail() {
//         return email;
//     }
//     public void setEmail(String email) {
//         this.email = email;
//     }
//     public String getAddress() {
//         return address;
//     }
//     public void setAddress(String address) {
//         this.address = address;
//     }
//     @Override
//     public String toString() {
//         return "CustomerInfo{" +
//             "orders=" + orders +  // <-- circular reference back to Order
//             '}';
//     }

//     @Override
//     public String toString() {
//         return "Order{" +
//             "customer=" + customer +   // If CustomerInfo.toString() calls Order.toString(), infinite loop!
//             ", items=" + items +
//             '}';
//     }

// }






// package com.example.webshop.model;

// // import lombok.AllArgsConstructor;
// import lombok.Data;
// // import lombok.NoArgsConstructor;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;

// @Data
// // @AllArgsConstructor
// // @NoArgsConstructor
// @Entity
// public class CustomerInfo {
//     @Id
//     private Long id; // Unique identifier for the customer info
//     private String name;
//     private String email;
//     private String address;
// }