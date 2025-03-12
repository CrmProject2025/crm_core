package com.crm.tehnomer.services.productService;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.crm.tehnomer.dtos.order.OrderCreateDto;
import com.crm.tehnomer.dtos.product.ProductCreateDto;
import com.crm.tehnomer.dtos.product.ProductGetDto;
import com.crm.tehnomer.dtos.product.UpdateProductDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.Product;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.OrderStatus;
import com.crm.tehnomer.filter.product.ProductFilter;
import com.crm.tehnomer.mappers.product.ProductMapper;
import com.crm.tehnomer.repositories.OrderRepository;
import com.crm.tehnomer.repositories.ProductRepository;
import com.crm.tehnomer.services.userService.UserService;
import com.crm.tehnomer.specification.ProductSpecifications;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<Product> getFilteredProducts(String model, String description, BigDecimal minPrice,
            BigDecimal maxPrice, Integer guarantee, Boolean deprecated) {
        Specification<Product> specification = ProductSpecifications.applyFilters(model,
                description, minPrice, maxPrice, guarantee, deprecated);
        return productRepository.findAll(specification);
    }

    public Page<ProductGetDto> getFilteredProductsWithPageable(String model, String description,
            BigDecimal minPrice, BigDecimal maxPrice, Integer guarantee,
            Boolean deprecated, Pageable pageable) {

        Specification<Product> specification = ProductSpecifications.applyFilters(model,
                description, minPrice, maxPrice, guarantee, deprecated);
        return productRepository.findAll(specification, pageable).map(productMapper::toDto);
    }

    @Cacheable(value = "productCache", key = "#id")
    public Product getProduct(Long id) {
        System.out.println("Fetching from DB...");
        return productRepository.findById(id).orElseThrow();
    }

    public void createProduct(ProductCreateDto productCreateDto) {
        Product product = productMapper.toEntity(productCreateDto);
        productRepository.save(product);

        logger.info("Creating product with id: {}", product.getId());
    }

    @CachePut(value = "productCache", key = "#product.id")
    public void updateProduct(Long id, UpdateProductDto updateProductDto) {
        Product product = productRepository.getReferenceById(id);
        productMapper.updateEntityFromDto(product, updateProductDto);
        productRepository.save(product);
    }

    @CacheEvict(value = "productCache", key = "#id")
    public void deleteProduct(Long id) {
        Product product = productRepository.getReferenceById(id);
        productRepository.delete(product);
    }

}
