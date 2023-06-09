package com.example.springadvancedtask2_2.controller;

import com.example.springadvancedtask2_2.entity.BrandTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.BrandTableDto;
import com.example.springadvancedtask2_2.service.BrandTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandTableController {

    @Autowired
    BrandTableService brandTableService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/api/brand")
    public ResponseEntity<List<BrandTable>> getBrandTable() {
        List<BrandTable> brandTable = brandTableService.getBrandTable();
        return ResponseEntity.ok(brandTable);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/api/brand/{id}")
    public ResponseEntity<BrandTable> getBrandTableById(@PathVariable Integer id) {
        return ResponseEntity.ok(brandTableService.getBrandTableById(id));
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("/api/brand")
    public ResponseEntity<ApiResponse> addBrandTable(@RequestBody BrandTableDto brandTableDto) {
        ApiResponse apiResponse = brandTableService.addBrandTable(brandTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/api/brand/{id}")
    public ResponseEntity<ApiResponse> editBrandTable(@PathVariable Integer id,
                                                      @RequestBody BrandTableDto brandTableDto) {
        ApiResponse apiResponse = brandTableService.editBrandTable(id, brandTableDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/api/brand/{id}")
    public ResponseEntity<ApiResponse> deleteBrandTable(@PathVariable Integer id) {
        ApiResponse apiResponse = brandTableService.deleteBrandTable(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
