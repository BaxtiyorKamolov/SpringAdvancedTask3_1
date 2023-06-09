package com.example.springadvancedtask2_2.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    @Column(nullable = false)
    private String quantity;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private ProductTable productTable;
}
