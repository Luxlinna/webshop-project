package com.example.webshop.service;

import com.example.webshop.model.Product;
import com.example.webshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testServiceNotNull() {
        assertNotNull(productService);
    }

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(0, products.size());
        verify(productRepository, times(1)).findAll();
    }
}










// package com.example.webshop.service;

// import com.example.webshop.model.Product;
// import com.example.webshop.repository.ProductRepository;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;

// import java.util.Collections;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @SpringBootTest
// public class ProductServiceTest {

//     // The real ProductService bean will be autowired (with mocked dependencies)
//     @Autowired
//     private ProductService productService;

//     // Mock your dependencies here so Spring can inject them into ProductService
//     @MockBean
//     private ProductRepository productRepository;

//     @MockBean
//     private OrderService orderService;

//     @Test
//     public void testServiceNotNull() {
//         // Make sure ProductService is correctly created
//         assertNotNull(productService);
//     }

//     @Test
//     public void testGetAllProducts() {
//         // Mock the repository response
//         when(productRepository.findAll()).thenReturn(Collections.emptyList());

//         List<Product> products = productService.getAllProducts();

//         assertNotNull(products);
//         assertEquals(0, products.size());

//         // Verify that findAll() was called exactly once
//         verify(productRepository, times(1)).findAll();
//     }
// }

