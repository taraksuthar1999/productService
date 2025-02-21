package com.example.productservice.services;

import com.example.productservice.exceptions.*;
import com.example.productservice.models.Cart;
import com.example.productservice.models.CartItem;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CartItemRepository;
import com.example.productservice.repositories.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartServiceDbImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    public CartServiceDbImpl(CartRepository cartRepository, ProductService productService,
                             CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart createCart(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        try{
            return cartRepository.save(cart);
        }catch (Exception error){
            throw new CartCreationFailedException("Error creating cart");
        }
    }
    @Override
    public Cart getCart(Long userId) {
        try{
            return cartRepository
                    .findByUserId(userId).orElseGet(()->createCart(userId));
        }catch (Exception error){
            throw new FetchCartException("Error fetching cart");
        }
    }

    @Override
    public void addToCart(Long userId, Long productId, Integer quantity) {
        try{
            Cart cart = getCart(userId);
            // if cartItem alreadyPresent in cart, update quantity
            CartItem presentInCart = cart
                    .getItems()
                    .stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElse(null);
            // else add new cartItem
            if(presentInCart != null) {
                presentInCart.setQuantity(presentInCart.getQuantity() + quantity);
                cartRepository.save(cart);
            } else {
                //fetch product
                Product product = productService.getById(productId);
                //add new cartItem
                CartItem cartItem = CartItem.builder()
                        .product(product)
                        .quantity(quantity)
                        .cart(cart)
                        .build();
                cartItemRepository.save(cartItem);
            }
        }catch (Exception e){
            throw new AddToCartException("Error adding product to cart. "+e.getMessage());
        }
    }

    @Override
    public CartItem updateCart(Long userId, Long productId, Integer quantity) {
        Cart cart = getCart(userId);
        try{
            CartItem presentInCart = cart
                    .getItems()
                    .stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElse(null);
            if (presentInCart != null) {
                presentInCart.setQuantity(quantity);
                cartRepository.save(cart);
                return presentInCart;
            }
            return null;
        }catch(Exception error){
            throw new FailedToUpdateCartException("Error updating cart. "+error.getMessage());
        }
    }

    @Override
    public void removeFromCart(Long userId, Long productId) {
        Cart cart = getCart(userId);
        try{
            cart
                .getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().ifPresent(cartItemRepository::delete);
        }catch(Exception error){
            throw new FailedToRemoveFromCartException("Error removing product from cart. "+error.getMessage());
        }
    }
}
