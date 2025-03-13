package com.crm.tehnomer.controlletTests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.crm.tehnomer.controllers.ProductController;
import com.crm.tehnomer.dtos.product.ProductCreateDto;
import com.crm.tehnomer.entities.Product;
import com.crm.tehnomer.mappers.product.ProductMapper;
import com.crm.tehnomer.repositories.ProductRepository;
import com.crm.tehnomer.repositories.UserRepository;
import com.crm.tehnomer.services.productService.ProductService;
import com.crm.tehnomer.settings.security.JwtUserDetailsService;
import com.crm.tehnomer.settings.security.jwt.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ProductMapper productMapper;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void createProduct_shouldReturnOk() throws Exception {
        String requestBody = """
                {
                  "name": "Product 1",
                  "model": "Model X",
                  "description": "Test product",
                  "price": 199.99,
                  "guarantee": 12,
                  "deprecated": false
                }
                """;

        mockMvc.perform(post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Product created"));

        verify(productService).createProduct(any(ProductCreateDto.class));
    }
}