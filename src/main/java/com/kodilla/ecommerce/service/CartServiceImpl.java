package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.CartRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.findByUserId(cart.getUser().getId()).orElse(save(cart));
    }

    @Override
    public List<Product> getElementsFromCart(Long id) {
        if(cartRepository.existsById(id)) {
            return cartRepository.findById(id).get().getProducts();
        } else {
            throw new NotFoundException("Cart id: " + id + "not found");
        }
    }

    @Override
    public void addProductToCart(Long id, Long productId) throws NotFoundException {
        if(cartRepository.existsById(id)) {
            //odkomentować po dodaniu productRepository

//            cartRepository.findById(id).get().getProducts()
//                    .add(productRepository.findById(productId)
//                            .orElseThrow(new NotFoundException("Product id: " + productId + " not found")));
        } else {
            throw new NotFoundException("Cart id: " + id + " not found");
        }
    }

    @Override
    public void deleteProductFromCart(Long id, Long productId) throws NotFoundException {
        if(cartRepository.existsById(id)) {
            //odkomentować po dodaniu productRepository

//            cartRepository.findById(id).get().getProducts()
//                    .remove(productRepository.findById(productId)
//                    .orElseThrow(new NotFoundException("Product id: " + productId + " not found")));
        } else {
            throw new NotFoundException("Cart id: " + id + "not found");
        }
    }

    @Override
    public void createOrderFromCart(Long id) {
        if(cartRepository.existsById(id)) {

            //odkomentować jak już będzie stworzone orderRepository

            //Order order = orderRepository.createOrder(cartRepository.findById(id).get());
            //if(order.findByCartId(id).isPresent() {
                cartRepository.deleteById(id);
            // }
        } else {
            throw new NotFoundException("Cart id: " + id + "not found");
        }
    }

    private Cart save (Cart cart) {
        return cartRepository.save(cart);
    }
}
