package com.example.productservice.repositories;

import com.example.productservice.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
   Optional<Cart> findByUserId(Long userId);

   @Override
   Cart save(Cart cart);

   @Modifying
   @Query("update CartItem c set c.quantity = ?3 where c.cart.id = ?1 and c.product.id = ?2")
   void updateCartItemQuantity(Long cartId, Long productId, Integer quantity);
}
