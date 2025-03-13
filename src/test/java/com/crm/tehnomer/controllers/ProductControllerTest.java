package com.crm.tehnomer.controllers;

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

// @SpringBootTest поднимается весь контекст, для тестов с бд
// @AutoConfigureMockMvc используется вместе с SpringBootTest

// @ExtendWith(MockitoExtension.class) без Spring вообще.
// @WebMvcTest(ProductController.class) поднимает только контроллеры, фильтры, Jackson.
//  Сервисы репозитории надо мокать. Для unit-тестов контроллеров.

// @Mock  юнит-тестах без Spring, для сервисов
// @InjectMocks Создает объект класса, который ты тестируешь,
//  Внедряет в него все моки, помеченные @Mock (автоматически).
// @MockBean Используется в тестах с Spring-контекстом (@SpringBootTest, @WebMvcTest).
// Подменяет реальный бин в контексте Spring на мок. deprecated
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void testCreateProduct_success2() throws Exception {
        ProductCreateDto dto = new ProductCreateDto();
        dto.setName("Smart Meter");
        dto.setModel("SM-1000");
        dto.setDescription("Advanced gas meter");
        dto.setPrice(BigDecimal.valueOf(150.50));
        dto.setGuarantee(24);
        dto.setDeprecated(false);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/product") // путь зависит от @RequestMapping класса
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Product created"));

        verify(productService).createProduct(any(ProductCreateDto.class));
    }

    @Test
    void testCreateProduct_success() {
        // given
        ProductCreateDto dto = new ProductCreateDto();
        dto.setName("Smart Meter");
        dto.setModel("SM-1000");
        // dto.setDescription("Advanced gas meter");
        dto.setPrice(BigDecimal.valueOf(199.99));
        dto.setGuarantee(24);
        dto.setDeprecated(false);

        Product mappedProduct = new Product();
        mappedProduct.setId(1L);
        mappedProduct.setName(dto.getName());
        mappedProduct.setModel(dto.getModel());
        // mappedProduct.setDescription(dto.getDescription());
        mappedProduct.setPrice(dto.getPrice());
        mappedProduct.setGuarantee(dto.getGuarantee());
        mappedProduct.setDeprecated(dto.getDeprecated());

        when(productMapper.toEntity(dto)).thenReturn(mappedProduct);
        when(productRepository.save(mappedProduct)).thenReturn(mappedProduct);

        // when
        productService.createProduct(dto);

        // then
        verify(productMapper).toEntity(dto);
        verify(productRepository).save(mappedProduct);
    }

    // @Test
    // void testCreateProduct_whenDtoInvalid_shouldThrowException() {
    // ProductCreateDto dto = new ProductCreateDto();
    // dto.setName(null); // например, обязательное поле

    // assertThrows(SomeValidationException.class, () ->
    // productService.createProduct(dto));
    // }
}