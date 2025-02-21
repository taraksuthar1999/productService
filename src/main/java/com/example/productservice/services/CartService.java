package com.example.productservice.services;

import com.example.productservice.models.Cart;
import com.example.productservice.models.CartItem;

public interface CartService {
    Cart createCart(Long userId);
    Cart getCart(Long userId);

    void addToCart(Long userId, Long productId, Integer quantity);

    CartItem updateCart(Long userId, Long productId, Integer quantity);
    void removeFromCart(Long userId, Long productId);
}
