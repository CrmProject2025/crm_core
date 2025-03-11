package com.crm.tehnomer.services.productService;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.crm.tehnomer.dtos.order.OrderCreateDto;
import com.crm.tehnomer.dtos.product.ProductCreateDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.Product;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.OrderStatus;
import com.crm.tehnomer.filter.product.ProductFilter;
import com.crm.tehnomer.repositories.OrderRepository;
import com.crm.tehnomer.repositories.ProductRepository;
import com.crm.tehnomer.services.userService.UserService;
import com.crm.tehnomer.specification.ProductSpecifications;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<Product> getFilteredProducts(String model, String description, BigDecimal minPrice,
            BigDecimal maxPrice, Integer guarantee, Boolean deprecated) {
        // Создаём спецификацию на основе фильтров
        Specification<Product> specification = ProductSpecifications.applyFilters(model, description, minPrice,
                maxPrice, guarantee, deprecated);

        // Получаем список продуктов, удовлетворяющих фильтру
        return productRepository.findAll(specification);
    }

    public void createProduct(ProductCreateDto productCreateDto) {
        // заменить на метод в маппере
        Product product = new Product();
        product.setName(productCreateDto.getName());
        product.setModel(productCreateDto.getModel());
        product.setDescription(productCreateDto.getDescription());
        product.setPrice(productCreateDto.getPrice());
        product.setGuarantee(productCreateDto.getGuarantee());
        product.setDeprecated(productCreateDto.getDeprecated());

        productRepository.save(product);
        logger.info("Creating product with id: {}", product.getId());

    }

    public void updateProduct(UpdateProductDto updateProductDto){
        Product product = getProduct

    }

    // public Page<Product> listProducts(OrderStatus status, int page, int size) {
    // Pageable pageable = PageRequest.of(page, size, Sort.by(
    // Sort.Direction.DESC, "dateCreate"));
    // return orderRepository.findAllByStatus(status, pageable);
    // }

}
