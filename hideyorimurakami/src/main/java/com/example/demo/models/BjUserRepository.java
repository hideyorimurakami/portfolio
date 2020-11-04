package com.example.demo.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BjUserRepository extends JpaRepository<BjUser, Integer> {
    BjUser findByUsername(String username);
}