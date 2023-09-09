package com.example.practice.repository;


import com.example.practice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    // where name = ?
    Optional<Role> findByName (String name);
}
