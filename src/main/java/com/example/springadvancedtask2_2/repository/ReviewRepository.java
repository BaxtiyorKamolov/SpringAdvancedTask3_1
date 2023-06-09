package com.example.springadvancedtask2_2.repository;

import com.example.springadvancedtask2_2.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
