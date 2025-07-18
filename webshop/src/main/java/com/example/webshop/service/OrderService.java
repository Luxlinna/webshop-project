package com.example.webshop.service;

import com.example.webshop.dto.OrderRequestDto;
import com.example.webshop.entity.CustomerEntity;
import com.example.webshop.entity.OrderEntity;
import com.example.webshop.entity.OrderItemEntity;
import com.example.webshop.exception.OrderNotFoundException;
import com.example.webshop.mapper.CustomerMapper;
import com.example.webshop.mapper.OrderMapper;
import com.example.webshop.model.Order;
import com.example.webshop.repository.CustomerRepository;
import com.example.webshop.repository.OrderRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    // Constructor injection for dependencies
    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper,
                        CustomerMapper customerMapper,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    // public OrderService(OrderRepository orderRepository,
    //                     OrderMapper orderMapper,
    //                     CustomerMapper customerMapper,
    //                     CustomerRepository customerRepository) {
    //     this.orderRepository = orderRepository;
    //     this.orderMapper = orderMapper;
    //     this.customerMapper = customerMapper;
    //     this.customerRepository = customerRepository;
    // }

    @Transactional
    public OrderEntity processOrder(OrderRequestDto orderRequest) {
        if (orderRequest == null || orderRequest.getCustomer() == null ||
            orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
            throw new IllegalArgumentException("Invalid order request");
        }

        // Try finding existing customer, or map a new one
        CustomerEntity customerEntity = customerRepository.findByEmail(orderRequest.getCustomer().getEmail())
            .orElseGet(() -> customerMapper.toEntity(orderRequest.getCustomer()));

        OrderEntity order = orderMapper.toEntity(orderRequest);
        order.setCustomer(customerEntity);

        // Defaults
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }
        if (order.getStatus() == null) {
            order.setStatus("NEW");
        }
        if (order.getAddress() == null) {
            order.setAddress(orderRequest.getCustomer().getAddress());
        }

        order.recalculateTotalPrice();

        // Save and Export
        OrderEntity savedOrder = orderRepository.save(order);
        saveOrderAsJson(orderRequest);
        return savedOrder;
    }

    public OrderEntity createOrder(OrderRequestDto orderRequestDto) {
        return processOrder(orderRequestDto);
    }

    public OrderEntity updateOrder(Long id, OrderRequestDto dto) {
        if (dto == null || dto.getCustomer() == null || dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new IllegalArgumentException("Invalid update request");
        }

        OrderEntity existingOrder = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        CustomerEntity customer = customerMapper.toEntity(dto.getCustomer());
        existingOrder.setCustomer(customer);

        List<OrderItemEntity> items = dto.getItems().stream()
            .map(itemDto -> {
                OrderItemEntity itemEntity = new OrderItemEntity();
                itemEntity.setProductId(itemDto.getProductId());
                itemEntity.setQuantity(itemDto.getQuantity());
                itemEntity.setPrice(itemDto.getPrice());
                itemEntity.setPriceAtPurchase(itemDto.getPriceAtPurchase());
                return itemEntity;
            }).toList();

        existingOrder.setItems(items);
        existingOrder.setOrderDate(LocalDateTime.now());
        existingOrder.recalculateTotalPrice();

        return orderRepository.save(existingOrder);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll().stream()
            .map(entity -> new Order(
                entity.getOrderId(),
                entity.getCustomer().getName(),
                entity.getTotalPrice()
            ))
            .collect(Collectors.toList());
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        orderRepository.deleteById(id);
    }

    public List<OrderEntity> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }

    public List<OrderEntity> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public void notifyCustomer(OrderEntity order) {
        String name = order.getCustomer().getName();
        String email = order.getCustomer().getEmail();
        String address = order.getCustomer().getAddress();

        System.out.println("Notifying " + name + " (" + email + ") about their order at " + address);
    }

    private void saveOrderAsJson(OrderRequestDto orderRequestDto) {
        try {
            File orderDir = new File("orders");
            if (!orderDir.exists()) {
                orderDir.mkdirs();
            }
            ObjectMapper mapper = new ObjectMapper();
            String uniqueFileName = "order-" + UUID.randomUUID() + ".json";
            File orderFile = new File(orderDir, uniqueFileName);
            mapper.writeValue(orderFile, orderRequestDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


