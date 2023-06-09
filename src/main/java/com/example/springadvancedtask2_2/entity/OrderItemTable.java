package com.example.springadvancedtask2_2.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItemTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false)
    private String price;

    @ManyToMany
    private List<ProductTable> productTables;

    @OneToMany
    private List<OrderTable> orderTables;
}
