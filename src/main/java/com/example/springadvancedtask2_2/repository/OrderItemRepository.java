package com.example.springadvancedtask2_2.repository;

import com.example.springadvancedtask2_2.entity.OrderItemTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemTable, Integer> {

}
