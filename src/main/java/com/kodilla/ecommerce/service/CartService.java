package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    public Cart save (Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart createCart(Cart cart) {
        return cartRepository.findByUserId(cart.getUser().getId()).orElse(save(cart));
    }

    public List<Product> getElementsFromCart(Long id) {
        if(cartRepository.existsById(id)) {
            return cartRepository.findById(id).get().getProducts();
        } else {
            throw new NotFoundException("Cart id: " + id + "not found");
        }
    }


}
