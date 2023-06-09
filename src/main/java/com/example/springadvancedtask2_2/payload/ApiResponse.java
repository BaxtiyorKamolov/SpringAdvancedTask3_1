package com.example.springadvancedtask2_2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
}
