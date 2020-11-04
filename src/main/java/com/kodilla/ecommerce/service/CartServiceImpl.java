package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.repository.OrderRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
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
        Cart cart = cartRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cart id: " + id + " not found"));
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"));
        Long quantityInStock = product.getQuantity();
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
            addProductToCart(cart, item);
        }
        changeProductQuantityInDB(product,  -quantity);
    }

    @Override
    public void decreaseProductQuantityInCart(Long id, Long productId, Long quantity) throws NotFoundException {
        Cart cart = cartRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cart id: " + id + " not found"));
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"));
        CartItem item = cartItemService.getCartItem(cart, product)
                .orElseThrow(() -> new NotFoundException("Cart Item not found. Cart id: " +
                        cart.getId() + ", product id: " + product.getId()));
        Long quantityInCart = item.getQuantity();

        if(quantityInCart <= quantity) {
            cartItemService.deleteCartItem(item.getId());
        } else {
            item.setQuantity(item.getQuantity() - quantity);
            cartItemService.updateCartItem(item);
        }
        changeProductQuantityInDB(product, quantity);
    }

    @Override
    public void createOrderFromCart(Long id) throws NotFoundException {
            orderService.createOrder(cartRepository
                                        .findById(id)
                                        .orElseThrow(() -> new NotFoundException("Cart id: " + id + " not found")));
            if(orderRepository.findByUserId(cartRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Cart id: " + id + " not found"))
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

    private void changeProductQuantityInDB(Product product, Long quantity) throws NotFoundException {
        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);
    }

    private boolean isProductInCart(Cart cart, Long productId) {
        return cart.getItems().stream()
                .map(CartItem::getProduct)
                .map(Product::getId)
                .anyMatch(value -> value.equals(productId));
    }

    private void addProductToCart(Cart cart, CartItem item) throws NotFoundException {
        cart.getItems().add(item);
        cartRepository.save(cart);
    }
}
