package com.example.springadvancedtask2_2.payload;

import com.example.springadvancedtask2_2.entity.UserTable;
//import jakarta.persistence.Column;
//import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTableDto {
    private Date orderDate;

    private String totalAmount;

    private List<Integer> userTablesIds;
}
