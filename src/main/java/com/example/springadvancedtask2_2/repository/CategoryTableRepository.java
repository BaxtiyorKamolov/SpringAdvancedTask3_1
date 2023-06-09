package com.example.springadvancedtask2_2.repository;

import com.example.springadvancedtask2_2.entity.CategoryTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryTableRepository extends JpaRepository<CategoryTable, Integer> {
}
