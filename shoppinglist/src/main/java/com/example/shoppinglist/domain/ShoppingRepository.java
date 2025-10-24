package com.example.shoppinglist.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
    List<Shopping> findByUserId(Long id);
}
