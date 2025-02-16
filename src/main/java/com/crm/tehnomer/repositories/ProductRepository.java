package com.crm.tehnomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.tehnomer.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{


    
}
