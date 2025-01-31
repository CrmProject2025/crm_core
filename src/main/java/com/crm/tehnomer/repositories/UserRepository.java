package com.crm.tehnomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.tehnomer.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);

    boolean existsByUsername(String username);

}
