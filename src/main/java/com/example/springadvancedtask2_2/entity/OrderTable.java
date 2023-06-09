package com.example.springadvancedtask2_2.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private String totalAmount;

    @OneToMany
    private List<UserTable> userTables;

}
