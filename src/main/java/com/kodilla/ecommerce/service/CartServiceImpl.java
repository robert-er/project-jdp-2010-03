package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.repository.OrderRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

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
            if(productRepository.existsById(productId)) {
                cartRepository.findById(id).get().getProducts().add(productRepository.findById(productId).get());
            } else {
                throw new NotFoundException("Product id: " + productId + " not found");
            }
        } else {
            throw new NotFoundException("Cart id: " + id + " not found");
        }
    }

    @Override
    public void deleteProductFromCart(Long id, Long productId) throws NotFoundException {
        if(cartRepository.existsById(id)) {
            if(productRepository.existsById(productId)) {
                cartRepository.findById(id).get().getProducts().remove(productRepository.findById(productId).get());
            } else {
                throw new NotFoundException("Product id: " + productId + " not found");
            }
        } else {
            throw new NotFoundException("Cart id: " + id + "not found");
        }
    }

    @Override
    public void createOrderFromCart(Long id) {
        if(cartRepository.existsById(id)) {
            orderService.createOrder(cartRepository.findById(id).get());
            if(orderRepository.findByUserId(cartRepository.findById(id).get().getUser().getId()).isPresent()) {
                cartRepository.deleteById(id);
             } else {
                throw new NotFoundException("Create order failed. Order with cart id: " + id + " not found");
            }
        } else {
            throw new NotFoundException("Cart id: " + id + "not found");
        }
    }

    private Cart save (Cart cart) {
        return cartRepository.save(cart);
    }
}
