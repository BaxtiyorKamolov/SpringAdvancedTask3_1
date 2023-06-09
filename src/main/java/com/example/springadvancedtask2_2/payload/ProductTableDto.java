package com.example.springadvancedtask2_2.payload;

import com.example.springadvancedtask2_2.entity.BrandTable;
import com.example.springadvancedtask2_2.entity.CategoryTable;
//import jakarta.persistence.Column;
//import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductTableDto {
    private String name;

    private String description;

    private String price;

    private String stockQuantity;

    private List<Integer> brandTablesId;

    private List<Integer> categoryTablesId;
}
