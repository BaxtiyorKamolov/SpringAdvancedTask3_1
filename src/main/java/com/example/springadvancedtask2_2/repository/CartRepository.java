package com.example.springadvancedtask2_2.repository;

import com.example.springadvancedtask2_2.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
