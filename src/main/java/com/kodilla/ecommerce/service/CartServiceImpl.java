package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.repository.OrderRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
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
    public List<Product> getElementsFromCart(Long id) throws NotFoundException {
            return cartRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Cart id: " + id + "not found"))
                    .getProducts();
    }

    @Override
    public void addProductToCart(Long id, Long productId, Long quantity) throws NotFoundException {
        if(cartRepository.existsById(id)) {
            if(productRepository.existsById(productId)) {
                if(productRepository.findById(productId).get().getQuantity() >=  quantity) {
                    Optional<Product> product = cartRepository.findById(id).get().getProducts().stream()
                            .filter(p -> p.getId().equals(productId))
                            .findFirst();
                    if(product.isPresent()) {
                        cartRepository.findById(id).get().getProducts().stream()
                                .filter(p -> p.getId().equals(productId))
                                .forEach(p -> p.setQuantity(p.getQuantity() + quantity));
                    } else {
                        product = productRepository.findById(productId);
                        product.get().setQuantity(quantity);
                        cartRepository.findById(id).get().getProducts().add(product.get());
                        changeProductQuantity(productId, quantity * -1L);
                    }
                } else {
                    throw new NotFoundException("Not enough quantity (" + quantity + ") of product id: " + productId);
                }
            } else {
                throw new NotFoundException("Product id: " + productId + " not found");
            }
        } else {
            throw new NotFoundException("Cart id: " + id + " not found");
        }
    }

    @Override
    public void deleteProductFromCart(Long id, Long productId, Long quantity) throws NotFoundException {
        if(cartRepository.existsById(id)) {
            if(productRepository.existsById(productId)) {
                Optional<Product> product = cartRepository.findById(id).get().getProducts().stream()
                        .filter(p -> p.getId().equals(productId))
                        .findFirst();
                if(product.isPresent()) {
                    cartRepository.findById(id).get().getProducts().stream()
                            .filter(p -> p.getId().equals(productId))
                            .forEach(p -> p.setQuantity(p.getQuantity() - quantity));
                    Long productQuantity = cartRepository.findById(id).get().getProducts().stream()
                            .filter(p -> p.getId().equals(productId))
                            .findFirst()
                            .get().getQuantity();
                    if(productQuantity <= 0L) {
                        cartRepository.findById(id).get().getProducts()
                                .remove(productRepository.findById(productId).get());
                    }
                    changeProductQuantity(productId, quantity);
                } else {
                    throw new NotFoundException("Product id: " + productId + " not found in the Cart");
                }
            } else {
                throw new NotFoundException("Product id: " + productId + " not found in Product database");
            }
        } else {
            throw new NotFoundException("Cart id: " + id + "not found");
        }
    }

    @Override
    public void createOrderFromCart(Long id) throws NotFoundException {
            orderService.createOrder(cartRepository
                                        .findById(id)
                                        .orElseThrow(() -> new NotFoundException("Cart id: " + id + "not found")));
            if(orderRepository.findByUserId(cartRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Cart id: " + id + "not found"))
                    .getUser().getId()).isPresent()) {
                deleteCartById(id);
            } else {
                throw new NotFoundException("Create order failed. Order with cart id: " + id + " not found");
            }
    }

    private void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    private Cart save (Cart cart) {
        return cartRepository.save(cart);
    }

    private void changeProductQuantity(Long productId, Long quantity) throws NotFoundException {
        productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"))
                .setQuantity(productRepository.findById(productId).get().getQuantity() + quantity);
    }

}