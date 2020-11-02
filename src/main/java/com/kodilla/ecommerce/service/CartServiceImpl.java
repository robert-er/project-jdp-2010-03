package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.repository.OrderRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    public void increaseProductQuantityInCart(Long id, Long productId, Long quantity) throws NotFoundException {
        Cart cart = cartRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cart id: " + id + "not found"));
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"));
        if(product.getQuantity() < quantity) {
            throw new NotFoundException("Not enough quantity (" + quantity + ") of product id: " + productId);
        }
        if(isProductInCart(cart, productId)) {
            changeProductQuantityInCart(id,productId,quantity);
        } else {
            product.setQuantity(quantity);
            addProductToCart(id, product);
        }
        changeProductQuantityInDB(productId, quantity * -1L);
    }

    @Override
    public void decreaseProductQuantityInCart(Long id, Long productId, Long quantity) throws NotFoundException {
        Cart cart = cartRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cart id: " + id + "not found"));
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"));

        Long productQuantityInCart = cart.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Product id: " + productId +
                        " not found in the Cart id: " + id))
                .getQuantity();

        if(productQuantityInCart <= quantity) {
            deleteProductFromCart(id, product);
        } else {
            changeProductQuantityInCart(id, productId, quantity * -1L);
        }
        changeProductQuantityInDB(productId, quantity);
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

    private void changeProductQuantityInDB(Long productId, Long quantity) throws NotFoundException {
        productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"))
                .setQuantity(productRepository.findById(productId).get().getQuantity() + quantity);
    }

    private void changeProductQuantityInCart(Long cartId, Long productId, Long quantity)  throws NotFoundException {
        cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Cart id: " + cartId + "not found"))
                .getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .forEach(p -> p.setQuantity(p.getQuantity() + quantity));
    }

    private boolean isProductInCart(Cart cart, Long productId) {
        return cart.getProducts().stream()
                .map(Product::getId)
                .anyMatch(value -> value.equals(productId));
    }

    private void addProductToCart(Long cartId, Product product) throws NotFoundException {
        cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Cart id: " + cartId + "not found"))
                .getProducts()
                .add(product);
    }

    private void deleteProductFromCart(Long cartId, Product product) throws NotFoundException {
        cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Cart id: " + cartId + "not found"))
                .getProducts()
                .remove(product);
    }
}
