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


