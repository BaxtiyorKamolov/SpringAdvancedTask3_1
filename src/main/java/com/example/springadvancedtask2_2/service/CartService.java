package com.example.springadvancedtask2_2.service;

import com.example.springadvancedtask2_2.entity.Cart;
import com.example.springadvancedtask2_2.entity.UserTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserTableService userTableService;

    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Integer id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        return optionalCart.orElse(null);
    }

    public ApiResponse deleteCartById(Integer id) {
        try {
            cartRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Mavjud emas", false);
        }
    }
}
