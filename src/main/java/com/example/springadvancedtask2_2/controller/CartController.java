package com.example.springadvancedtask2_2.controller;

import com.example.springadvancedtask2_2.entity.Cart;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/api/cart")
    public ResponseEntity<List<Cart>> getAllCart() {
        List<Cart> allCart = cartService.getAllCart();
        return ResponseEntity.ok(allCart);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/api/cart/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Integer id) {
        Cart cartById = cartService.getCartById(id);
        return ResponseEntity.ok(cartById);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/api/cart/{id}")
    public ResponseEntity<ApiResponse> deleteCartById(@PathVariable Integer id) {
        ApiResponse apiResponse = cartService.deleteCartById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
