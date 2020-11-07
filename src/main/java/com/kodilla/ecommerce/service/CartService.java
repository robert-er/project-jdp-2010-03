package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.exception.NotFoundException;

import java.util.List;

public interface CartService {

    Cart createCart(Cart cart);
    List<CartItem> getElementsFromCart(Long id) throws NotFoundException;
    void increaseProductQuantityInCart(Long id, Long productId, Long quantity) throws NotFoundException;
    void decreaseProductQuantityInCart(Long id, Long productId, Long quantity) throws NotFoundException;
    void createOrderFromCart(Long id);
    Cart findById(Long id);
}
