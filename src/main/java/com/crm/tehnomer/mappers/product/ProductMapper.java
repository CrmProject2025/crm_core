package com.crm.tehnomer.mappers.product;

import java.util.List;

import org.mapstruct.Mapper;

import com.crm.tehnomer.dtos.product.ProductGetDto;
import com.crm.tehnomer.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductGetDto toDto(Product product);

    List<ProductGetDto> toDtoList(List<Product> products);
}