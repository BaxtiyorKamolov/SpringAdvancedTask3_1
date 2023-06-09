package com.example.springadvancedtask2_2.controller;

import com.example.springadvancedtask2_2.entity.OrderTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.OrderTableDto;
import com.example.springadvancedtask2_2.service.OrderTableService;
//import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderTableController {

    @Autowired
    OrderTableService orderTableService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/api/order")
    public ResponseEntity<List<OrderTable>> getOrderTable() {
        List<OrderTable> orderTable = orderTableService.getOrderTable();
        return ResponseEntity.ok(orderTable);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/api/order/{id}")
    public ResponseEntity<OrderTable> getOrderTableById(@PathVariable Integer id) {
        OrderTable orderTableById = orderTableService.getOrderTableById(id);
        return ResponseEntity.ok(orderTableById);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("/api/order")
    public ResponseEntity<ApiResponse> addOrderTable(@RequestBody OrderTableDto orderTableDto) {
        ApiResponse apiResponse = orderTableService.addOrderTable(orderTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/api/order/{id}")
    public ResponseEntity<ApiResponse> editOrderTable(@PathVariable Integer id,
                                                      @RequestBody OrderTableDto orderTableDto) {
        ApiResponse apiResponse = orderTableService.editOrderTable(id, orderTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/api/order/{id}")
    public ResponseEntity<ApiResponse> deleteOrderTable(@PathVariable Integer id) {
        ApiResponse apiResponse = orderTableService.deleteOrderTable(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
