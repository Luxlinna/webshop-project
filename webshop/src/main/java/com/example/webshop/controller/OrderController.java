package com.example.webshop.controller;

import com.example.webshop.dto.OrderRequestDto;
import com.example.webshop.dto.OrderResponseDto;
import com.example.webshop.entity.CustomerEntity;
import com.example.webshop.entity.OrderEntity;
import com.example.webshop.service.OrderService;
import com.example.webshop.mapper.OrderMapper;
import com.example.webshop.repository.OrderRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.webshop.model.Order;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
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


    // @PostMapping("/api/orders")
    // public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderRequest) {
    //     CustomerEntity customer = new CustomerEntity();
    //     customer.setName(orderRequest.getCustomer().getName());
    //     customer.setEmail(orderRequest.getCustomer().getEmail());
    //     customer.setAddress(orderRequest.getCustomer().getAddress());

    //     OrderEntity order = new OrderEntity();
    //     order.setCustomer(customer);
    //     order.setItems(/* map your order items here */);
    //     order.recalculateTotalPrice();

    //     OrderEntity savedOrder = orderRepository.save(order);
    //     return ResponseEntity.ok(savedOrder);
    // }



    // @PostMapping
    // public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
    //     OrderEntity savedOrder = orderService.createOrder(orderRequestDto);
    //     OrderResponseDto result = orderMapper.toResponseDto(savedOrder);
    //     return ResponseEntity.ok(result);
    // }

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










// @RestController
// @RequestMapping("/api/orders")
// @CrossOrigin(origins = "http://localhost:5173")
// public class OrderController {

//     private final OrderRepository orderRepository;
//     private final OrderService orderService;
//     private final OrderMapper orderMapper;

//     @Autowired
//     public OrderController(OrderService orderService, OrderMapper orderMapper, OrderRepository orderRepository) {
//         this.orderService = orderService;
//         this.orderMapper = orderMapper;
//         this.orderRepository = orderRepository;
//     }

//     /**
//      * Get all orders.
//      */

//     @GetMapping
//     public List<Order> getAllOrders() {
//         return orderService.getOrders()
//         // return orderRepository.findAll();
//     }

//     /**
//      * Create a new order.
//      */
//     @PostMapping
//     public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
//         // Save the entity
//         OrderEntity savedOrder = orderService.createOrder(orderRequestDto);
//         // Map to a response DTO
//         OrderResponseDto result = orderMapper.toResponseDto(savedOrder);
//         // Return
//         return ResponseEntity.ok(result);
//     }

//     /**
//      * Health check endpoint.
//      */
//     @GetMapping("/ping")
//     public String healthCheck() {
//         return "Controller is working.";
//     }

//     /**
//      * Get order details by ID.
//      */
//     @GetMapping("/{orderId:\\d+}")
//     public ResponseEntity<?> getOrderDetails(@PathVariable("orderId") Long orderId) {
//         OrderEntity order = orderService.getOrderById(orderId);
//         if (order == null) {
//             return ResponseEntity.notFound().build();
//         }
//         return ResponseEntity.ok(orderMapper.toResponseDto(order));
//     }
// }









// package com.example.webshop.controller;

// import com.example.webshop.dto.OrderRequestDto;
// import com.example.webshop.dto.OrderResponseDto;
// import com.example.webshop.entity.OrderEntity;
// import com.example.webshop.service.OrderService;
// import com.example.webshop.mapper.OrderMapper;
// import com.example.webshop.model.Order;


// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.beans.factory.annotation.Autowired;

// @RestController
// // @RequestMapping("/api/orders")
// @CrossOrigin(origins = "http://localhost:5173")
// public class OrderController {

//     private final OrderService orderService;
//     private final OrderMapper orderMapper;

//     @Autowired
//     public OrderController(OrderService orderService, OrderMapper orderMapper) {
//         this.orderService = orderService;
//         this.orderMapper = orderMapper;
//     }

//     /**
//      * Create a new order.
//      */
//     @PostMapping("/orders")
//     public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
//         // 1️⃣ Save the order entity
//         OrderEntity savedOrder = orderService.createOrder(orderRequestDto);

//         // 2️⃣ Map the saved entity to a response DTO
//         OrderResponseDto result = orderMapper.toResponseDto(savedOrder);

//         // 3️⃣ Return the result
//         return ResponseEntity.ok(result);
//     }

//     // @PostMapping("/orders")
//     // public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
//     //     // OrderEntity savedOrder = orderService.createOrder(orderRequestDto);
//     //     OrderResponseDto result = orderService.createOrder(orderMapper.toResponseDto(savedOrder));
//     //     return ResponseEntity.ok(result);
//     // }

//     /**
//      * Health check endpoint.
//      */
//     @GetMapping("/ping")
//     public String healthCheck() {
//         return "Controller is working.";
//     }

//     /**
//      * Get order details by ID.
//      * The path is restricted to digits only using a regex.
//      */
//     @GetMapping("/{orderId:\\d+}")
//     public ResponseEntity<?> getOrderDetails(@PathVariable("orderId") Long orderId) {
//         OrderEntity order = orderService.getOrderById(orderId);
//         if (order == null) {
//             return ResponseEntity.notFound().build();
//         }
//         return ResponseEntity.ok(orderMapper.toResponseDto(order));
//     }
// }









// package com.example.webshop.controller;

// import com.example.webshop.dto.OrderRequestDto;
// import com.example.webshop.dto.OrderResponseDto;
// import com.example.webshop.mapper.OrderMapper;
// import com.example.webshop.entity.OrderEntity;
// import com.example.webshop.service.OrderService;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.beans.factory.annotation.Autowired;

// import java.util.List;
// import java.util.stream.Collectors;
// import java.time.LocalDateTime;


// @RestController
// @RequestMapping("/api/orders")
// @CrossOrigin(origins = "http://localhost:5173")
// public class OrderController {

//     private final OrderService orderService;
//     private final OrderMapper orderMapper;

//     @Autowired
//     public OrderController(OrderService orderService, OrderMapper orderMapper) {
//         this.orderService = orderService;
//         this.orderMapper = orderMapper;
//     }

//     @PostMapping
//     public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
//         OrderEntity savedOrder = orderService.createOrder(orderRequestDto);
//         OrderResponseDto response = orderMapper.toResponseDto(savedOrder);
//         return ResponseEntity.ok(response);
//     }

//     @GetMapping("/ping")
//     public String healthCheck() {
//         return "Controller is working.";
//     }

//     @GetMapping("/{id:\\d+}")
//     public ResponseEntity<?> getOrderDetails(@PathVariable Long id) {
//         OrderEntity order = orderService.getOrderById(id);
//         if (order == null) {
//             return ResponseEntity.notFound().build();
//         }
//         return ResponseEntity.ok(orderMapper.toResponseDto(order));
//     }
// }




// @RestController
// @RequestMapping("/api/orders")
// @CrossOrigin(origins = "http://localhost:5173")
// public class OrderController {

//     private final OrderService orderService;
//     private final OrderMapper orderMapper;

//     @Autowired
//     public OrderController(OrderService orderService, OrderMapper orderMapper) {
//         this.orderService = orderService;
//         this.orderMapper = orderMapper;
//     }

//     @PostMapping
//     public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
//         // Let the service do all the work
//         OrderEntity savedOrder = orderService.createOrder(orderRequestDto);
//         OrderResponseDto response = orderMapper.toResponseDto(savedOrder);

//         System.out.println("Received request: " + orderRequestDto);

//         return ResponseEntity.ok(response);
//     }

//     @GetMapping("/ping")
//     public String healthCheck() {
//         return "Controller is working.";
//     }

//     @GetMapping("/{id:\\d+}")
//     public ResponseEntity<?> getOrderDetails(@PathVariable Long id) {
//         OrderEntity order = orderService.getOrderById(id);

//         String customerName = order.getCustomer().getName();
//         String customerEmail = order.getCustomer().getEmail();
//         String customerAddress = order.getCustomer().getAddress();

//         // You can use these values for a DTO, a response, or just log them
//         System.out.println("Customer Name: " + customerName);
//         System.out.println("Customer Email: " + customerEmail);
//         System.out.println("Customer Address: " + customerAddress);

//         return ResponseEntity.ok(orderMapper.toResponseDto(order));

//         // return ResponseEntity.ok(order);
//     }
// }


