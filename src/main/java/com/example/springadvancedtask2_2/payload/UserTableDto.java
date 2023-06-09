package com.example.springadvancedtask2_2.payload;

//import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTableDto {

    private String name;


    private String email;


    private String password;


    private String address;


    private String phoneNumber;
}
