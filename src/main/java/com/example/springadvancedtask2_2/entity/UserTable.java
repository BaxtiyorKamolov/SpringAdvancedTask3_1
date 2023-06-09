package com.example.springadvancedtask2_2.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.sql.rowset.spi.SyncResolver;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;


}
