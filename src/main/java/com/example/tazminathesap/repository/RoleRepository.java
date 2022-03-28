package com.example.tazminathesap.repository;

import java.util.Optional;

import com.example.tazminathesap.model.ERole;
import com.example.tazminathesap.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByName(ERole name);
}
