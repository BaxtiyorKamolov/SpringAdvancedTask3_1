package com.example.springadvancedtask2_2.controller;

import com.example.springadvancedtask2_2.entity.UserTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.UserTableDto;
import com.example.springadvancedtask2_2.service.UserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserTableController {

    @Autowired
    UserTableService userTableService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/api/userTable")
    public ResponseEntity<List<UserTable>> getUserTable() {
        List<UserTable> userTable = userTableService.getUserTable();
        return ResponseEntity.ok(userTable);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/api/userTable/{id}")
    public ResponseEntity<UserTable> getUserTableById(@PathVariable Integer id) {
        UserTable userTableById = userTableService.getUserTableById(id);
        return ResponseEntity.ok(userTableById);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("/api/userTable")
    public ResponseEntity<ApiResponse> addUserTable(@RequestBody UserTableDto userTableDto) {
        ApiResponse apiResponse = userTableService.addUserTable(userTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/api/userTable/{id}")
    public ResponseEntity<ApiResponse> editUserTable(@PathVariable Integer id,
                                                     @RequestBody UserTableDto userTableDto) {
        ApiResponse apiResponse = userTableService.editUserTable(id, userTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/api/userTable/{id}")
    public ResponseEntity<ApiResponse> deleteUserTable(@PathVariable Integer id) {
        ApiResponse apiResponse = userTableService.deleteUserTable(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
