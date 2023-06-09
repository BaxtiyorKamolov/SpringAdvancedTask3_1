package com.example.springadvancedtask2_2.repository;

import com.example.springadvancedtask2_2.entity.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTableRepository extends JpaRepository<OrderTable, Integer> {

}
