package com.example.springadvancedtask2_2.controller;

import com.example.springadvancedtask2_2.entity.ProductTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.ProductTableDto;
import com.example.springadvancedtask2_2.service.ProductTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.AcceptPendingException;
import java.util.List;

@RestController
public class ProductTableController {
    @Autowired
    ProductTableService productTableService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/api/product")
    public ResponseEntity<List<ProductTable>> getProductTable() {
        List<ProductTable> productTable = productTableService.getProductTable();
        return ResponseEntity.ok(productTable);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/api/product/{id}")
    public ResponseEntity<ProductTable> getProductTableById(@PathVariable Integer id) {
        ProductTable productTableById = productTableService.getProductTableById(id);
        return ResponseEntity.ok(productTableById);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("/api/product")
    public ResponseEntity<ApiResponse> addProductTable(@RequestBody ProductTableDto productTableDto) {
        ApiResponse apiResponse = productTableService.addProductTable(productTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/api/product/{id}")
    public ResponseEntity<ApiResponse> editProductTable(@PathVariable Integer id,
                                                        @RequestBody ProductTableDto productTableDto) {
        ApiResponse apiResponse = productTableService.editProductTable(id, productTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/api.product/{id}")
    public ResponseEntity<ApiResponse> deleteProductTable(@PathVariable Integer id) {
        ApiResponse apiResponse = productTableService.deleteProductTable(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
