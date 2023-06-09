package com.example.springadvancedtask2_2.controller;

import com.example.springadvancedtask2_2.entity.CartItem;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.CartItemDto;
import com.example.springadvancedtask2_2.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/api/cartItem")
    public ResponseEntity<List<CartItem>> getCartItem() {
        List<CartItem> cartItem = cartItemService.getCartItem();
        return ResponseEntity.ok(cartItem);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/api/cartItem/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Integer id) {
        CartItem cartItemById = cartItemService.getCartItemById(id);
        return ResponseEntity.ok(cartItemById);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("/api/cartItem")
    public ResponseEntity<ApiResponse> addCartItem(@RequestBody CartItemDto cartItemDto) {
        ApiResponse apiResponse = cartItemService.addCartItem(cartItemDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/api/cartItem/{id}")
    public ResponseEntity<ApiResponse> editCartItem(@PathVariable Integer id,
                                                    @RequestBody CartItemDto cartItemDto) {
        ApiResponse apiResponse = cartItemService.editCartItem(id, cartItemDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/api/cartItem/{id}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Integer id) {
        ApiResponse apiResponse = cartItemService.deleteCartItem(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
