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










// package com.example.webshop.service;

// import com.example.webshop.dto.OrderRequestDto;
// import com.example.webshop.entity.CustomerEntity;
// import com.example.webshop.entity.OrderEntity;
// import com.example.webshop.entity.OrderItemEntity;
// import com.example.webshop.exception.OrderNotFoundException;
// import com.example.webshop.mapper.CustomerMapper;
// import com.example.webshop.mapper.OrderMapper;
// import com.example.webshop.repository.OrderRepository;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.example.webshop.model.Order;
// import com.example.webshop.repository.CustomerRepository;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.io.File;
// import java.io.IOException;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.UUID;
// import java.util.stream.Collectors;

// @Service
// public class OrderService {

//     private final OrderRepository orderRepository;
//     private final OrderMapper orderMapper;
//     private final CustomerMapper customerMapper;
//     private final CustomerRepository customerRepository;

//     public OrderService(OrderRepository orderRepository,
//                         OrderMapper orderMapper,
//                         CustomerMapper customerMapper,
//                         CustomerRepository customerRepository) {
//         this.orderRepository = orderRepository;
//         this.orderMapper = orderMapper;
//         this.customerMapper = customerMapper;
//         this.customerRepository = customerRepository;
//     }


//     @Autowired
//     private CustomerRepository customerRepository;  // Inject this in constructor

//     @Transactional
//     public OrderEntity processOrder(OrderRequestDto orderRequest) {
//         if (orderRequest == null || orderRequest.getCustomer() == null ||
//             orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
//             throw new IllegalArgumentException("Invalid order request");
//         }

//         // Check if customer exists by unique identifier (email assumed here)
//         CustomerEntity customerEntity = customerRepository
//             .findByEmail(orderRequest.getCustomer().getEmail())
//             .orElseGet(() -> customerMapper.toEntity(orderRequest.getCustomer()));

//         // If new customer, customerEntity will be transient and cascade persist will save it
//         OrderEntity order = orderMapper.toEntity(orderRequest);

//         order.setCustomer(customerEntity);

//         // Ensure required fields are set
//         if (order.getOrderDate() == null) {
//             order.setOrderDate(LocalDateTime.now());
//         }

//         if (order.getStatus() == null) {
//             order.setStatus("NEW");
//         }

//         if (order.getAddress() == null) {
//             // Assuming order address comes from customer or orderRequest - set appropriately
//             order.setAddress(orderRequest.getCustomer().getAddress());
//         }

//         order.recalculateTotalPrice();

//         return orderRepository.save(order);
//     }



//     // public OrderEntity processOrder(OrderRequestDto orderRequest) {
//     //     if (orderRequest == null || orderRequest.getCustomer() == null ||
//     //         orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
//     //         throw new IllegalArgumentException("Invalid order request");
//     //     }

//     //     // ✅ Map DTO -> Entity
//     //     OrderEntity order = orderMapper.toEntity(orderRequest);

//     //     // ✅ Explicitly map the customer entity
//     //     CustomerEntity customerEntity = customerMapper.toEntity(orderRequest.getCustomer());
//     //     order.setCustomer(customerEntity);

//     //     // ✅ Recalculate totals
//     //     order.recalculateTotalPrice();

//     //     // ✅ Save to the database
//     //     OrderEntity savedOrder = orderRepository.save(order);

//     //     // ✅ Export as JSON (optional)
//     //     saveOrderAsJson(orderRequest);

//     //     return savedOrder;
//     // }

//     public OrderEntity createOrder(OrderRequestDto orderRequestDto) {
//         return processOrder(orderRequestDto);
//     }

//     public OrderEntity updateOrder(Long id, OrderRequestDto dto) {
//         if (dto == null || dto.getCustomer() == null || dto.getItems() == null || dto.getItems().isEmpty()) {
//             throw new IllegalArgumentException("Invalid update request");
//         }

//         OrderEntity existingOrder = orderRepository.findById(id)
//             .orElseThrow(() -> new OrderNotFoundException(id));

//         // ✅ Update the customer
//         CustomerEntity customer = customerMapper.toEntity(dto.getCustomer());
//         existingOrder.setCustomer(customer);

//         // ✅ Update the order items
//         List<OrderItemEntity> items = dto.getItems().stream()
//             .map(itemDto -> {
//                 OrderItemEntity itemEntity = new OrderItemEntity();
//                 itemEntity.setProductId(itemDto.getProductId());
//                 itemEntity.setQuantity(itemDto.getQuantity());
//                 itemEntity.setPrice(itemDto.getPrice());
//                 itemEntity.setPriceAtPurchase(itemDto.getPriceAtPurchase());
//                 return itemEntity;
//             }).toList();

//         existingOrder.setItems(items);
//         existingOrder.setOrderDate(LocalDateTime.now());
//         existingOrder.recalculateTotalPrice();

//         return orderRepository.save(existingOrder);
//     }

//     public List<Order> getOrders() {
//         List<OrderEntity> orderEntities = orderRepository.findAll();

//         return orderEntities.stream()
//             .map(entity -> new Order(
//                 entity.getOrderId(),
//                 entity.getCustomer().getName(),
//                 entity.getTotalPrice()
//             ))
//             .collect(Collectors.toList());
//     }

//     public List<OrderEntity> getAllOrders() {
//         return orderRepository.findAll();
//     }

//     public OrderEntity getOrderById(Long id) {
//         return orderRepository.findById(id)
//             .orElseThrow(() -> new OrderNotFoundException(id));
//     }

//     public OrderEntity save(OrderEntity order) {
//         // implementation to save order (e.g., via repository)
//         return orderRepository.save(order);
//     }

//     public void deleteOrder(Long id) {
//         if (!orderRepository.existsById(id)) {
//             throw new OrderNotFoundException(id);
//         }
//         orderRepository.deleteById(id);
//     }

//     public List<OrderEntity> getOrdersByCustomerId(Long customerId) {
//         return orderRepository.findByCustomer_Id(customerId);
//     }

//     public List<OrderEntity> getOrdersByStatus(String status) {
//         return orderRepository.findByStatus(status);
//     }

//     public void notifyCustomer(OrderEntity order) {
//         String name = order.getCustomer().getName();
//         String email = order.getCustomer().getEmail();
//         String address = order.getCustomer().getAddress();

//         System.out.println("Notifying " + name + " (" + email + ") about their order at " + address);
//     }

//     private void saveOrderAsJson(OrderRequestDto orderRequestDto) {
//         try {
//             File orderDir = new File("orders");
//             if (!orderDir.exists()) {
//                 orderDir.mkdirs();
//             }
//             ObjectMapper mapper = new ObjectMapper();
//             String uniqueFileName = "order-" + UUID.randomUUID() + ".json";
//             File orderFile = new File(orderDir, uniqueFileName);
//             mapper.writeValue(orderFile, orderRequestDto);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }










// package com.example.webshop.service;

// import com.example.webshop.dto.OrderRequestDto;
// import com.example.webshop.entity.CustomerEntity;
// import com.example.webshop.entity.OrderEntity;
// import com.example.webshop.entity.OrderItemEntity;
// import com.example.webshop.exception.OrderNotFoundException;
// import com.example.webshop.mapper.CustomerMapper;
// import com.example.webshop.mapper.OrderMapper;
// import com.example.webshop.repository.OrderRepository;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.springframework.stereotype.Service;

// import java.io.File;
// import java.io.IOException;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.UUID;

// @Service
// public class OrderService {

//     private final OrderRepository orderRepository;
//     private final OrderMapper orderMapper;
//     private final CustomerMapper customerMapper;

//     public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, CustomerMapper customerMapper) {
//         this.orderRepository = orderRepository;
//         this.orderMapper = orderMapper;
//         this.customerMapper = customerMapper;
//     }

//     public OrderEntity processOrder(OrderRequestDto orderRequest) {
//         if (orderRequest == null || orderRequest.getCustomer() == null ||
//             orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
//             throw new IllegalArgumentException("Invalid order request");
//         }

//         OrderEntity order = orderMapper.toEntity(orderRequest);
//         order.recalculateTotalPrice();

//         return orderRepository.save(order);
//     }




//     // public OrderEntity processOrder(OrderRequestDto dto) {
//     //     // ✅ Validate request
//     //     if (dto == null || dto.getCustomer() == null ||
//     //         dto.getItems() == null || dto.getItems().isEmpty()) {
//     //         throw new IllegalArgumentException("Invalid order request");
//     //     }

//     //     // ✅ Create and map Customer
//     //     CustomerEntity customer = customerMapper.toEntity(dto.getCustomer());

//     //     // ✅ Create Order
//     //     OrderEntity order = new OrderEntity();
//     //     order.setCustomer(customer);
//     //     order.setItems(orderMapper.toItemEntities(dto.getItems())); // adjust method accordingly
//     //     order.setOrderDate(LocalDateTime.now());
//     //     order.setStatus("PENDING"); // Or any initial status
//     //     order.recalculateTotalPrice();

//     //     // ✅ Save Order
//     //     OrderEntity savedOrder = orderRepository.save(order);

//     //     // ✅ Export as JSON (optional)
//     //     saveOrderAsJson(dto);

//     //     return savedOrder;
//     // }

//     public OrderEntity createOrder(OrderRequestDto orderRequestDto) {
//         return processOrder(orderRequestDto);
//     }

//     public OrderEntity updateOrder(Long id, OrderRequestDto dto) {
//         if (dto == null || dto.getCustomer() == null
//             || dto.getItems() == null || dto.getItems().isEmpty()) {
//             throw new IllegalArgumentException("Invalid update request");
//         }

//         OrderEntity existingOrder = orderRepository.findById(id)
//             .orElseThrow(() -> new OrderNotFoundException(id));

//         CustomerEntity customer = customerMapper.toEntity(dto.getCustomer());
//         existingOrder.setCustomer(customer);

//         List<OrderItemEntity> items = dto.getItems().stream()
//             .map(itemDto -> {
//                 OrderItemEntity itemEntity = new OrderItemEntity();
//                 itemEntity.setProductId(itemDto.getProductId());
//                 itemEntity.setQuantity(itemDto.getQuantity());
//                 itemEntity.setPrice(itemDto.getPrice());
//                 itemEntity.setPriceAtPurchase(itemDto.getPriceAtPurchase());
//                 return itemEntity;
//             }).toList();

//         existingOrder.setItems(items);
//         existingOrder.setOrderDate(LocalDateTime.now());
//         existingOrder.recalculateTotalPrice();

//         return orderRepository.save(existingOrder);
//     }

//     public List<OrderEntity> getAllOrders() {
//         return orderRepository.findAll();
//     }

//     public OrderEntity getOrderById(Long id) {
//         return orderRepository.findById(id)
//             .orElseThrow(() -> new OrderNotFoundException(id));
//     }

//     public void deleteOrder(Long id) {
//         if (!orderRepository.existsById(id)) {
//             throw new OrderNotFoundException(id);
//         }
//         orderRepository.deleteById(id);
//     }

//     public List<OrderEntity> getOrdersByCustomerId(Long customerId) {
//         return orderRepository.findByCustomer_Id(customerId);
//     }

//     public List<OrderEntity> getOrdersByStatus(String status) {
//         return orderRepository.findByStatus(status);
//     }

//     // In your OrderService.java
//     public void notifyCustomer(OrderEntity order) {
//         String name = order.getCustomer().getName();
//         String email = order.getCustomer().getEmail();
//         String address = order.getCustomer().getAddress();

//         // Do something with this data:
//         System.out.println("Notifying " + name + " (" + email + ") about their order at " + address);
//     }


//     // ✅ Export order request as JSON
//     private void saveOrderAsJson(OrderRequestDto orderRequestDto) {
//         try {
//             File orderDir = new File("orders"); // Directory path
//             if (!orderDir.exists()) {
//                 orderDir.mkdirs();
//             }

//             ObjectMapper mapper = new ObjectMapper();
//             String uniqueFileName = "order-" + UUID.randomUUID() + ".json";
//             File orderFile = new File(orderDir, uniqueFileName);
//             mapper.writeValue(orderFile, orderRequestDto);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }



//         // // ✅ Map DTO to entity
//         // OrderEntity entity = orderMapper.toEntity(dto);
//         // entity.setOrderDate(LocalDateTime.now());

//         // // ✅ Ensure customer entity is attached
//         // entity.setCustomer(customerMapper.toEntity(dto.getCustomer()));
//         // entity.recalculateTotalPrice();

//         // // ✅ Save the entity
//         // OrderEntity savedOrder = orderRepository.save(entity);
//         // // ✅ Notify customer
//         // notifyCustomer(savedOrder);

//         // // ✅ Export as JSON
//         // saveOrderAsJson(dto);

//         // return savedOrder;
//     }
