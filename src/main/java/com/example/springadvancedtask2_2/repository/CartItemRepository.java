package com.example.springadvancedtask2_2.repository;


import com.example.springadvancedtask2_2.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
