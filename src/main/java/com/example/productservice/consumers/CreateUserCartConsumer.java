package com.example.productservice.consumers;

import com.example.productservice.services.CartService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreateUserCartConsumer {
    private final CartService cartService;

    public CreateUserCartConsumer(CartService cartService) {
        this.cartService = cartService;
    }

    @KafkaListener(topics="create-user-cart", groupId="product-service")
    public void consume(String userId) {
        cartService.createCart(Long.parseLong(userId));
    }
}
