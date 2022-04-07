package com.example.tazminathesap.repository;

import java.util.List;

import com.example.tazminathesap.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    
    public List<User> findByName(String name);
    public User findByEmail(String email);
    Boolean existsByEmail(String email);
}
