package com.example.productservice.repositories;

import com.example.productservice.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem save(CartItem cartItem);
    void delete(CartItem cartItem);
}