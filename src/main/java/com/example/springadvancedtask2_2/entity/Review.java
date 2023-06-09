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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false)
    private String comment;

    @OneToMany
    private List<UserTable> userTables;

    @OneToMany
    private List<ProductTable> productTables;
}
