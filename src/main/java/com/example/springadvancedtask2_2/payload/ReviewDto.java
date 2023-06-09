package com.example.springadvancedtask2_2.payload;

import com.example.springadvancedtask2_2.entity.ProductTable;
import com.example.springadvancedtask2_2.entity.UserTable;
//import jakarta.persistence.Column;
//import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.Inet4Address;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private String rating;

    private String comment;

    private List<Integer> userTables;

    private List<Integer> productTables;
}
