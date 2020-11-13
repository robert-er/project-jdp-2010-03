package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.exception.NotValidException;
import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final CartItemService cartItemService;

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.findByUserId(cart.getUser().getId()).orElseGet(() -> save(cart));
    }

    @Override
    public List<CartItem> getElementsFromCart(Long id) throws NotFoundException {
        return cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cart id: " + id + " not found"))
                .getItems();
    }

    @Override
    public void increaseProductQuantityInCart(Long id, Long productId, Long quantity) throws NotFoundException {
        validateQuantity(quantity);
        Cart cart = cartRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cart id: " + id + " not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"));
        Long quantityInStock = product.getQuantityInStock();
        if(quantityInStock < quantity) {
            throw new NotFoundException("Not enough quantity (" + quantity + ") of product id: " + productId);
        }
        if(isProductInCart(cart, productId)) {
            CartItem item = cart.getItems().stream()
                    .filter(value -> value.getProduct().getId().equals(productId))
                    .collect(Collectors.toList())
                    .get(0);
            item.setQuantity(item.getQuantity() + quantity);
            cartItemService.updateCartItem(item);
        } else {
            CartItem item = new CartItem(cart, product, quantity);
            addCartItemToCart(cart, item);
        }
    }

    @Override
    public void decreaseProductQuantityInCart(Long id, Long productId, Long quantity) throws NotFoundException {
        validateQuantity(quantity);
        Cart cart = cartRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cart id: " + id + " not found"));
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"));
        CartItem item = cartItemService.getCartItem(cart, product)
                .orElseThrow(() -> new NotFoundException("Cart Item not found. Cart id: " +
                        cart.getId() + ", product id: " + product.getId()));
        Long quantityInCart = item.getQuantity();

        if(quantityInCart <= quantity) {
            removeCartItemFromCart(cart, item);
            cartItemService.deleteCartItem(item.getId());
        } else {
            item.setQuantity(item.getQuantity() - quantity);
            cartItemService.updateCartItem(item);
        }
    }

    @Override
    public void createOrderFromCart(Long id) throws NotFoundException {
        boolean isOrderCreated = orderService.createOrder(cartRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Cart id: " + id + " not found")));
        if(isOrderCreated) {
            deleteCartById(id);
        } else {
            throw new NotFoundException("Create order failed. Order with cart id: " + id + " not found");
        }
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cart id: " + id + " not found"));
    }

    private void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    private Cart save (Cart cart) {
        return cartRepository.save(cart);
    }

    private boolean isProductInCart(Cart cart, Long productId) {
        return cart.getItems().stream()
                .map(CartItem::getProduct)
                .map(Product::getId)
                .anyMatch(value -> value.equals(productId));
    }

    private void addCartItemToCart(Cart cart, CartItem item) {
        cart.getItems().add(item);
        cartRepository.save(cart);
    }

    private void removeCartItemFromCart(Cart cart, CartItem item) {
        cart.getItems().remove(item);
        cartRepository.save(cart);
    }

    private void validateQuantity(Long quantity) throws NotValidException {
        if(quantity <= 0L) {
            throw new NotValidException("Requested quantity " + quantity + " can not be lower or equal 0");
        }
    }
}