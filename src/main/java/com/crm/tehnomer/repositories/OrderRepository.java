package com.crm.tehnomer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.tehnomer.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

    Page<Order> findAllByOrderByDateCreateDesc(Pageable pageable);

    
}
