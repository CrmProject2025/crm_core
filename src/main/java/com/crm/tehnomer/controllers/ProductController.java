package com.crm.tehnomer.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.tehnomer.dtos.ResponseDto;
import com.crm.tehnomer.dtos.product.ProductCreateDto;
import com.crm.tehnomer.dtos.product.ProductGetDto;
import com.crm.tehnomer.dtos.product.UpdateProductDto;
import com.crm.tehnomer.entities.Product;
import com.crm.tehnomer.mappers.product.ProductMapper;
import com.crm.tehnomer.repositories.UserRepository;
import com.crm.tehnomer.services.productService.ProductService;

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

    @GetMapping("filter/pageable")
    public Page<ProductGetDto> getRequestedProducts(
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer guarantee,
            @RequestParam(required = false) Boolean deprecated,
            @PageableDefault(page = 0, size = 10, sort = "price") Pageable pageable) {

        return productService.getFilteredProductsWithPageable(model, description, minPrice, maxPrice,
                guarantee, deprecated, pageable);
    }

    @GetMapping("/{id}")
    public ProductGetDto getProduct(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        return productMapper.toDto(product);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createProduct(
            @Validated @RequestBody ProductCreateDto productCreateDto) {
        productService.createProduct(productCreateDto);
        return ResponseEntity.ok(ResponseDto.toDto("Product created"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable("id") Long id,
            @Validated @RequestBody UpdateProductDto updateProductDto) {
        productService.updateProduct(id, updateProductDto);
        return ResponseEntity.ok(ResponseDto.toDto("Product updated with id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ResponseDto.toDto("Product deleted with id: " + id));
    }

    // далее работаем с добавлением складов, добавление продуктов на склад

}
