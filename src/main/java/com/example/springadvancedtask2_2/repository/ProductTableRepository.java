package com.example.springadvancedtask2_2.repository;

import com.example.springadvancedtask2_2.entity.ProductTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTableRepository extends JpaRepository<ProductTable, Integer> {
}
