package com.crm.tehnomer.specification;

import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import com.crm.tehnomer.entities.Product;

public class ProductSpecifications {

    public static Specification<Product> modelContains(String model) {
        return (root, query, criteriaBuilder) -> {
            if (model == null || model.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("model"), "%" + model + "%");
        };
    }

    public static Specification<Product> descriptionContains(String description) {
        return (root, query, criteriaBuilder) -> {
            if (description == null || description.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("description"), "%" + description + "%");
        };
    }

    public static Specification<Product> priceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return null;
            }
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            } else if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            } else {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            }
        };
    }

    public static Specification<Product> guaranteeEquals(Integer guarantee) {
        return (root, query, criteriaBuilder) -> {
            if (guarantee == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("guarantee"), guarantee);
        };
    }

    public static Specification<Product> deprecatedEquals(Boolean deprecated) {
        return (root, query, criteriaBuilder) -> {
            if (deprecated == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("deprecated"), deprecated);
        };
    }

    // Объединение всех условий в одну спецификацию
    public static Specification<Product> applyFilters(String model, String description, BigDecimal minPrice,
            BigDecimal maxPrice, Integer guarantee, Boolean deprecated) {
        return Specification.where(modelContains(model))
                .and(descriptionContains(description))
                .and(priceBetween(minPrice, maxPrice))
                .and(guaranteeEquals(guarantee))
                .and(deprecatedEquals(deprecated));
    }
}
