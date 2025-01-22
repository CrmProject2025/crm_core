package com.crm.tehnomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.tehnomer.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
