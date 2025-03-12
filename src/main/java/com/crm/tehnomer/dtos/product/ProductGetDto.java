package com.crm.tehnomer.dtos.product;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductGetDto {
    private long id;
    private String name;
    private String model;
    private String description;
    private BigDecimal price;
    private Integer guarantee;
    private Boolean deprecated;
}
