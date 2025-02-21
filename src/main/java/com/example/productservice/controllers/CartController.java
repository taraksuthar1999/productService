package com.example.productservice.controllers;


import com.example.productservice.dtos.*;
import com.example.productservice.dtos.ResponseStatus;
import com.example.productservice.models.Cart;
import com.example.productservice.models.CartItem;
import com.example.productservice.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto<CartResponseDto>> getCart(@RequestHeader("x-user") String userId) {
        Cart cart = cartService.getCart(Long.parseLong(userId));

        return ResponseEntity.ok().body(new ResponseDto<>(
                ResponseStatus.SUCCESS,
                "Cart fetched successfully.",
                CartResponseDto.fromCart(cart)
        ));
    }

    @GetMapping("/add")
    public ResponseEntity<ResponseDto<Object>> addProductToCart(@RequestHeader("x-user") String userId, @RequestParam Long productId, @RequestParam(defaultValue = "1") Integer quantity) {
        cartService.addToCart(Long.parseLong(userId), productId, quantity);
        return ResponseEntity.ok().body(new ResponseDto<>(
                ResponseStatus.SUCCESS,
                "Product added to cart successfully.",
                null
        ));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<ResponseDto<Object>> removeProductFromCart(@RequestHeader("x-user") String userId,@PathVariable Long productId) {
        cartService.removeFromCart(Long.parseLong(userId), productId);

        return ResponseEntity.ok().body(new ResponseDto<>(
                ResponseStatus.SUCCESS,
                "Product removed from cart successfully.",
                null
        ));
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDto<UpdateQuantityResponseDto>> updateProductQuantity(@RequestHeader("x-user") String userId, @RequestBody updateQuantityRequestDto updateQuantityRequestDto) {
        CartItem cartItem = cartService.updateCart(Long.parseLong(userId), updateQuantityRequestDto.getProductId(), updateQuantityRequestDto.getQuantity());
        UpdateQuantityResponseDto updateQuantityResponseDto = null;
        if(cartItem != null){
            updateQuantityResponseDto = new UpdateQuantityResponseDto();
            updateQuantityResponseDto.setProductId(cartItem.getProduct().getId());
            updateQuantityResponseDto.setQuantity(cartItem.getQuantity());
        }
        return ResponseEntity.ok().body(new ResponseDto<>(
                ResponseStatus.SUCCESS,
                "Cart updated successfully.",
                updateQuantityResponseDto
        ));
    }
}
