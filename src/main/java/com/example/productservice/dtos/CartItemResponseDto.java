package com.example.productservice.dtos;

import com.example.productservice.dtos.product.ProductResponseDto;
import com.example.productservice.models.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartItemResponseDto {
    private Long id;
    private ProductResponseDto product;
    private Integer quantity;

    public static List<CartItemResponseDto> fromCartItems(List<CartItem> cartItems){
        List<CartItemResponseDto> cartItemResponseDtos = new ArrayList<>();
        for(CartItem cartItem: cartItems){
            CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
            cartItemResponseDto.setId(cartItem.getId());
            cartItemResponseDto.setProduct(ProductResponseDto.fromProduct(cartItem.getProduct()));
            cartItemResponseDto.setQuantity(cartItem.getQuantity());
            cartItemResponseDtos.add(cartItemResponseDto);
        }
        return cartItemResponseDtos;
    }
}
