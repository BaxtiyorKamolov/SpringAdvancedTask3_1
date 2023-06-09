package com.example.springadvancedtask2_2.service;

import com.example.springadvancedtask2_2.entity.Cart;
import com.example.springadvancedtask2_2.entity.CartItem;
import com.example.springadvancedtask2_2.entity.ProductTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.CartItemDto;
import com.example.springadvancedtask2_2.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ProductTableService productTableService;

    public List<CartItem> getCartItem() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Integer id) {
        Optional<CartItem> byId = cartItemRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(cartItemDto.getQuantity());

        Cart cartById = cartService.getCartById(cartItemDto.getCartId());
        cartItem.setCart(cartById);

        ProductTable productTableById = productTableService.getProductTableById(cartItemDto.getCartId());
        cartItem.setProductTable(productTableById);

        cartItemRepository.save(cartItem);
        return new ApiResponse("Qoshildi", true);
    }

    public ApiResponse editCartItem(Integer id, CartItemDto cartItemDto) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (optionalCartItem.isEmpty()) {
            return new ApiResponse("Mavjud emas", false);
        }
        CartItem cartItem = optionalCartItem.get();
        cartItem.setQuantity(cartItemDto.getQuantity());

        Cart cartById = cartService.getCartById(cartItemDto.getCartId());
        cartItem.setCart(cartById);

        ProductTable productTableById = productTableService.getProductTableById(cartItemDto.getProductTableId());
        cartItem.setProductTable(productTableById);

        cartItemRepository.save(cartItem);
        return new ApiResponse("Qoshildi", true);

    }

    public ApiResponse deleteCartItem(Integer id) {
        try {
            cartItemRepository.deleteById(id);
            return new ApiResponse("Ochirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Mavjud emas", false);
        }
    }
}
