package com.example.webshop.controller;

import com.example.webshop.dto.OrderRequestDto;

import com.example.webshop.entity.OrderEntity;
import com.example.webshop.service.OrderService;
import com.example.webshop.mapper.OrderMapper;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.webshop.model.Order;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    /**
     * Create a new order.
     */

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderRequest) {
        OrderEntity savedOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(savedOrder);
    }


    @GetMapping("/ping")
    public String healthCheck() {
        return "Controller is working.";
    }

    @GetMapping("/{orderId:\\d+}")
    public ResponseEntity<?> getOrderDetails(@PathVariable Long orderId) {
        OrderEntity order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderMapper.toResponseDto(order));
    }
}


