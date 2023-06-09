package com.example.springadvancedtask2_2.controller;

import com.example.springadvancedtask2_2.entity.CategoryTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.CategoryTableDto;
import com.example.springadvancedtask2_2.service.CategoryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryTableController {
    @Autowired
    CategoryTableService categoryTableService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/api/category")
    public ResponseEntity<List<CategoryTable>> getCategoryTable() {
        List<CategoryTable> categoryTable = categoryTableService.getCategoryTable();
        return ResponseEntity.ok(categoryTable);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/api/category/{id}")
    public ResponseEntity<CategoryTable> getCategoryTableById(@PathVariable Integer id) {
        CategoryTable categoryTableById = categoryTableService.getCategoryTableById(id);
        return ResponseEntity.ok(categoryTableById);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("/api/category")
    public ResponseEntity<ApiResponse> addCategoryTable(@RequestBody CategoryTableDto categoryTableDto) {
        ApiResponse apiResponse = categoryTableService.addCategoryTable(categoryTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/api/categroy/{id}")
    public ResponseEntity<ApiResponse> editCategoryTable(@PathVariable Integer id,
                                                         @RequestBody CategoryTableDto categoryTableDto) {
        ApiResponse apiResponse = categoryTableService.editCategoryTable(id, categoryTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/api/category/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryTable(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryTableService.deleteCategoryTable(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
