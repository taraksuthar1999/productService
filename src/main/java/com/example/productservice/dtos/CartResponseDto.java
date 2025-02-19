package com.example.productservice.dtos;

import com.example.productservice.models.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CartResponseDto {
    private Long id;
    private Double totalAmount;
    private Integer totalItems;
    private List<CartItemResponseDto> items;

    public static CartResponseDto fromCart(Cart cart){
        return CartResponseDto.builder().id(cart.getId())
                .totalAmount(cart.getTotalAmount())
                .totalItems(cart.getTotalItems())
                .items(CartItemResponseDto.fromCartItems(cart.getItems()))
                .build();
    }
}
