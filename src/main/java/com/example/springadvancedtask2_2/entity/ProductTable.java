package com.example.springadvancedtask2_2.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String stockQuantity;

    @OneToMany
    private List<BrandTable> brandTables;

    @OneToMany
    private List<CategoryTable> categoryTables;

}
