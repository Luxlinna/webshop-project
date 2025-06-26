package com.example.webshop.service;

import com.example.webshop.dto.OrderRequestDto;
import com.example.webshop.dto.CustomerInfoDto;
import com.example.webshop.dto.OrderItemDto;
import com.example.webshop.entity.OrderEntity;
import com.example.webshop.entity.CustomerEntity;
import com.example.webshop.entity.OrderItemEntity;
import com.example.webshop.mapper.OrderMapper;
import com.example.webshop.mapper.CustomerMapper;
import com.example.webshop.repository.CustomerRepository;
import com.example.webshop.repository.OrderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private OrderService orderService;

    private OrderRequestDto orderRequestDto;
    private OrderEntity orderEntity;
    private CustomerEntity customerEntity;

    @BeforeEach
    void setUp() {
        // Setup DTOs and Entities for tests
        CustomerInfoDto customerDto = new CustomerInfoDto();
        customerDto.setName("John Doe");
        customerDto.setEmail("john@example.com");

        OrderItemDto itemDto = new OrderItemDto();
        itemDto.setProductId(1L);
        itemDto.setQuantity(2);
        itemDto.setPrice(new BigDecimal("10.00"));
        itemDto.setPriceAtPurchase(new BigDecimal("9.50"));

        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setCustomer(customerDto);
        orderRequestDto.setItems(List.of(itemDto));

        customerEntity = new CustomerEntity();
        customerEntity.setName("John Doe");
        customerEntity.setEmail("john@example.com");

        OrderItemEntity itemEntity = new OrderItemEntity();
        itemEntity.setProductId(1L);
        itemEntity.setQuantity(2);
        itemEntity.setPrice(new BigDecimal("10.00"));
        itemEntity.setPriceAtPurchase(new BigDecimal("9.50"));

        orderEntity = new OrderEntity();
        orderEntity.setCustomer(customerEntity);
        orderEntity.setItems(List.of(itemEntity));
    }

    @Test
    void testCreateOrder() {
        // Mock the mapper conversions
        when(customerMapper.toEntity(orderRequestDto.getCustomer())).thenReturn(customerEntity);
        when(orderMapper.toEntity(orderRequestDto)).thenReturn(orderEntity);
        lenient().when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);



        // When
        OrderEntity savedOrder = orderService.createOrder(orderRequestDto);

        // Then
        verify(orderRepository, times(1)).save(any(OrderEntity.class));  // <-- Verify save called

        // // More flexible stub - allow any OrderEntity
        // when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        // // Call the method under test
        // OrderEntity savedOrder = orderService.createOrder(orderRequestDto);

        // Verify and assert results
        assertNotNull(savedOrder);
        assertEquals(customerEntity, savedOrder.getCustomer());
        assertEquals(1, savedOrder.getItems().size());
        // verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

}









// package com.example.webshop.service;

// import com.example.webshop.dto.OrderRequestDto;
// import com.example.webshop.dto.CustomerInfoDto;
// import com.example.webshop.dto.OrderItemDto;
// import com.example.webshop.entity.OrderEntity;
// import com.example.webshop.entity.CustomerEntity;
// import com.example.webshop.entity.OrderItemEntity;
// import com.example.webshop.mapper.OrderMapper;
// import com.example.webshop.mapper.CustomerMapper;
// import com.example.webshop.repository.OrderRepository;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import java.math.BigDecimal;
// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.extension.ExtendWith;

// @ExtendWith(MockitoExtension.class)
// class OrderServiceTest {

//     @Mock
//     private OrderRepository orderRepository;

//     @Mock
//     private OrderMapper orderMapper;

//     @Mock
//     private CustomerMapper customerMapper;

//     @InjectMocks
//     private OrderService orderService;

//     private OrderRequestDto orderRequestDto;
//     private OrderEntity orderEntity;
//     private CustomerEntity customerEntity;

//     @BeforeEach
//     void setUp() {
//         // Setup DTOs and Entities for tests
//         CustomerInfoDto customerDto = new CustomerDto();
//         customerDto.setName("John Doe");
//         customerDto.setEmail("john@example.com");
        
//         OrderItemDto itemDto = new OrderItemDto();
//         itemDto.setProductId(1L);
//         itemDto.setQuantity(2);
//         itemDto.setPrice(new BigDecimal("10.00"));
//         itemDto.setPriceAtPurchase(new BigDecimal("9.50"));

//         orderRequestDto = new OrderRequestDto();
//         orderRequestDto.setCustomer(customerDto);
//         orderRequestDto.setItems(List.of(itemDto));

//         customerEntity = new CustomerEntity();
//         customerEntity.setName("John Doe");
//         customerEntity.setEmail("john@example.com");

//         OrderItemEntity itemEntity = new OrderItemEntity();
//         itemEntity.setProductId(1L);
//         itemEntity.setQuantity(2);
//         itemEntity.setPrice(new BigDecimal("10.00"));
//         itemEntity.setPriceAtPurchase(new BigDecimal("9.50"));

//         orderEntity = new OrderEntity();
//         orderEntity.setCustomer(customerEntity);
//         orderEntity.setItems(List.of(itemEntity));
//     }

//     @Test
//     void testCreateOrder() {
//         // Mock the mapper conversions
//         when(customerMapper.toEntity(orderRequestDto.getCustomer())).thenReturn(customerEntity);
//         when(orderMapper.toEntity(orderRequestDto)).thenReturn(orderEntity);

//         // Mock the repository save
//         when(orderRepository.save(orderEntity)).thenReturn(orderEntity);

//         // Call the method under test
//         OrderEntity savedOrder = orderService.createOrder(orderRequestDto);

//         // Verify and assert results
//         assertNotNull(savedOrder);
//         assertEquals(customerEntity, savedOrder.getCustomer());
//         assertEquals(1, savedOrder.getItems().size());
//         verify(orderRepository, times(1)).save(orderEntity);
//     }
// }









// package com.example.webshop.service;

// import com.example.webshop.model.CustomerInfo;
// import com.example.webshop.model.Order;
// import com.example.webshop.model.OrderItem;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @ExtendWith(MockitoExtension.class)
// class OrderServiceTest {

//     private OrderService orderService;

//     @BeforeEach
//     void setUp() {
//         // You can mock dependencies here if needed, or use null if createOrder doesn't depend on them.
//         orderService = new OrderService(null, null); // since createOrder does not use repository or mapper
//     }

//     @Test
//     void testCreateOrder() {
//         CustomerInfo customer = new CustomerInfo();
//         // Set necessary customer fields if needed

//         OrderItem item1 = new OrderItem();
//         // Set necessary fields on item1

//         List<OrderItem> items = List.of(item1);

//         OrderEntity order = orderService.createOrder(customer, items);

//         assertNotNull(order);
//         assertEquals(customer, order.getCustomer());
//         assertEquals(items, order.getItems());
//     }
// }

