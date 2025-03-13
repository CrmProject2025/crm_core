package com.crm.tehnomer.dtos.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto {

    public ProductCreateDto(@NotBlank String name, @NotBlank String model, @NotBlank String description,
            @NotNull BigDecimal price, @NotNull Integer guarantee, @NotNull Boolean deprecated) {
        this.name = name;
        this.model = model;
        this.description = description;
        this.price = price;
        this.guarantee = guarantee;
        this.deprecated = deprecated;
    }

    public ProductCreateDto() {
    }

    @NotBlank
    private String name;

    @NotBlank
    private String model;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer guarantee;

    @NotNull
    private Boolean deprecated;

}
