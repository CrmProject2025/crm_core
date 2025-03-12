package com.crm.tehnomer.mappers.product;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.crm.tehnomer.dtos.product.ProductCreateDto;
import com.crm.tehnomer.dtos.product.ProductGetDto;
import com.crm.tehnomer.dtos.product.UpdateProductDto;
import com.crm.tehnomer.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductGetDto toDto(Product product);

    Product toEntity(ProductCreateDto productCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(@MappingTarget Product product, UpdateProductDto updateProductDto);

    List<ProductGetDto> toDtoList(List<Product> products);
}