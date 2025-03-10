package com.crm.tehnomer.filter.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.crm.tehnomer.dtos.user.UserClientGetDto;
import com.crm.tehnomer.dtos.user.UserGetDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.OrderStatus;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilter {

    private String model;

    private String description;

    private BigDecimal minPrice;
    
    private BigDecimal maxPrice;

    private Integer guarantee;

    private Boolean deprecated;

}
