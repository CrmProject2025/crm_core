package com.crm.tehnomer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.enums.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long>{

    Page<Order> findAllByStatus(OrderStatus status, Pageable pageable);

    
}
