package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.exception.NotFoundException;

import java.util.Optional;

public interface CartItemService {

    CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(CartItem cartItem) throws NotFoundException;
    Optional<CartItem> getCartItem(Cart cart, Product product);
    public void deleteCartItem(Long id);
}
