package com.example.springadvancedtask2_2.payload;

import com.example.springadvancedtask2_2.entity.UserTable;
//import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private List<Integer> userTables;
}
