package com.example.springadvancedtask2_2.payload;

import com.example.springadvancedtask2_2.entity.OrderTable;
import com.example.springadvancedtask2_2.entity.ProductTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemTableDto {
    private String quantity;

    private String price;

    private List<Integer> productTables;

    private List<Integer> orderTables;
}
