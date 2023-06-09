package com.example.springadvancedtask2_2.repository;

import com.example.springadvancedtask2_2.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTableRepository extends JpaRepository<UserTable, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndUserIdNot(String phoneNumber, Integer userId);
}
