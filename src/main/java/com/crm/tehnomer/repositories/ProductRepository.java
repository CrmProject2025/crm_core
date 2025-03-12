package com.crm.tehnomer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.Product;
import com.crm.tehnomer.entities.enums.OrderStatus;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // Page<Product> findAllByStatus(OrderStatus status, Pageable pageable);

}
