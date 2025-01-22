package com.crm.tehnomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.tehnomer.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
    
}
