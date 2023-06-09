package com.example.springadvancedtask2_2.payload;

import com.example.springadvancedtask2_2.entity.Cart;
import com.example.springadvancedtask2_2.entity.ProductTable;
//import jakarta.persistence.Column;
//import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartItemDto {
    private String quantity;

    private Integer cartId;

    private Integer productTableId;
}
