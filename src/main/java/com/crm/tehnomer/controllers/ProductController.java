package com.crm.tehnomer.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.tehnomer.dtos.ResponseDto;
import com.crm.tehnomer.dtos.order.OrderCreateDto;
import com.crm.tehnomer.dtos.order.OrderGetDto;
import com.crm.tehnomer.dtos.order.TakeRequestedOrderBySalerDto;
import com.crm.tehnomer.dtos.product.ProductCreateDto;
import com.crm.tehnomer.dtos.product.ProductGetDto;
import com.crm.tehnomer.dtos.product.UpdateProductDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.Product;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.OrderStatus;
import com.crm.tehnomer.mappers.product.ProductMapper;
import com.crm.tehnomer.repositories.UserRepository;
import com.crm.tehnomer.services.orderService.OrderService;
import com.crm.tehnomer.services.productService.ProductService;
import com.crm.tehnomer.services.userService.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private UserRepository userRepository;
    private ProductService productService;
    private ProductMapper productMapper;

    @GetMapping("/filter")
    public List<ProductGetDto> getFilteredProducts(@RequestParam(required = false) String model,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer guarantee,
            @RequestParam(required = false) Boolean deprecated) {

        List<Product> products = productService.getFilteredProducts(model, description, minPrice, maxPrice, guarantee,
                deprecated);
        return productMapper.toDtoList(products);

    }

    // разобраться с mapper, по факту там логика как у меня в методах dto
    // добавить фильтры и page в одном контроллере

    // @GetMapping("")
    // public Page<OrderGetDto> getRequestedProducts(
    // @RequestParam(defaultValue = "REQUEST_STATUS") OrderStatus status,
    // @RequestParam(defaultValue = "0") int page,
    // @RequestParam(defaultValue = "10") int size) {

    // Page<Order> orders = productService.listProducts(status, page, size);
    // return OrderGetDto.toPageOrders(orders);
    // }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createProduct(
            @Validated @RequestBody ProductCreateDto productCreateDto) {
        productService.createProduct(productCreateDto);
        return ResponseEntity.ok(ResponseDto.toDto("Product created"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable("id") Long id,
            @Validated @RequestBody UpdateProductDto updateProductDto,
            Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName());
        productService.updateProduct(updateProductDto);
    }

    // @DeleteMa("/{id}")
    // public ResponseEntity<ResponseDto>
    // takeRequestedOrderBySaler(@PathVariable("id") Long id,
    // @Validated @RequestBody TakeRequestedOrderBySalerDto
    // takeRequestedOrderBySalerDto,
    // Authentication auth) {
    // User currentUser = userRepository.findByUsername(auth.getName());
    // orderService.editOrderStatus(id, takeRequestedOrderBySalerDto, currentUser);

    // return ResponseEntity.ok(ResponseDto.toDto("Request status changed to
    // PROCESSING_BY_THE_SELLER_STATUS"));
    // }
    // для продавца:

    // достать все продукты
    // добавить новый продукт
    // изменить параметры продукта
    // удалить продукт

    // далее работаем с добавлением складов, добавление продуктов на склад

}
