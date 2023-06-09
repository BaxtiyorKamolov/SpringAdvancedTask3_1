package com.example.springadvancedtask2_2.controller;

import com.example.springadvancedtask2_2.entity.OrderItemTable;
import com.example.springadvancedtask2_2.entity.OrderTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.OrderItemTableDto;
import com.example.springadvancedtask2_2.service.OrderItemTableService;
import com.example.springadvancedtask2_2.service.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderItemTableController {
    @Autowired
    OrderItemTableService orderItemTableService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/api/orderItem")
    public ResponseEntity<List<OrderItemTable>> getOrderItemTable() {
        List<OrderItemTable> orderItemTable = orderItemTableService.getOrderItemTable();
        return ResponseEntity.ok(orderItemTable);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/api/orderItem/{id}")
    public ResponseEntity<OrderItemTable> getOrderItemTableById(@PathVariable Integer id) {
        OrderItemTable orderItemTableById = orderItemTableService.getOrderItemTableById(id);
        return ResponseEntity.ok(orderItemTableById);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("api/orderItem")
    public ResponseEntity<ApiResponse> addOrderItemTable(@RequestBody OrderItemTableDto orderItemTableDto) {
        ApiResponse apiResponse = orderItemTableService.addOrderItemTable(orderItemTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/api/oderItem/{id}")
    public ResponseEntity<ApiResponse> editOrderItemTable(@PathVariable Integer id,
                                                          @RequestBody OrderItemTableDto orderItemTableDto) {
        ApiResponse apiResponse = orderItemTableService.editOrderItemTable(id, orderItemTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/api/orderItem/{id}")
    public ResponseEntity<ApiResponse> deleteOrderItemTable(@PathVariable Integer id) {
        ApiResponse apiResponse = orderItemTableService.deleteOrderItemTable(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
