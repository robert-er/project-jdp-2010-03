package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.findByCartAndProduct(cartItem.getCart(), cartItem.getProduct())
                .orElse(save(cartItem));
    }

    public CartItem updateCartItem(CartItem cartItem) throws  NotFoundException {
        CartItem returnedCartItem = cartItemRepository.findById(cartItem.getId())
                .orElseThrow(() -> new NotFoundException("Cart item id: " + cartItem.getId() + " not found"));
        returnedCartItem.setCart(cartItem.getCart());
        returnedCartItem.setProduct(cartItem.getProduct());
        returnedCartItem.setQuantity(cartItem.getQuantity());
        save(returnedCartItem);
        return returnedCartItem;
    }

    public Optional<CartItem> getCartItem(Cart cart, Product product) {
        return cartItemRepository.findByCartAndProduct(cart, product);
    }

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    private CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}
