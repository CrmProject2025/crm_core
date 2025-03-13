package com.crm.tehnomer.unitTests;



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

import com.crm.tehnomer.dtos.product.ProductCreateDto;
import com.crm.tehnomer.entities.Product;
import com.crm.tehnomer.mappers.product.ProductMapper;
import com.crm.tehnomer.repositories.ProductRepository;
import com.crm.tehnomer.services.productService.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct_shouldSaveProduct() {
        ProductCreateDto dto = new ProductCreateDto("Название", "Модель", "Описание", new BigDecimal("100.00"), 12, false);
        Product product = new Product();

        when(productMapper.toEntity(dto)).thenReturn(product);

        productService.createProduct(dto);

        verify(productRepository).save(product);
    }
}