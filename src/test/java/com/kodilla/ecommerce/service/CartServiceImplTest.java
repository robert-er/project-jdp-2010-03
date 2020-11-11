package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.*;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import com.kodilla.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.OptionalLong;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CartServiceImplTest {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    private User user = new User("nickname", "name", "surname", "email", false);
    private Product product = new Product("product_title",
            BigDecimal.ONE, "product_description", 10L);
    private CartItem item = new CartItem();

    @Test
    public void should_save_cart_in_database() {
        //Given
        User createdUser = userRepository.save(user);
        Cart cart = new Cart(user);
        //When
        Cart createdCart = cartService.createCart(cart);
        //Then
        assertNotEquals(createdCart.getId(), null);
        //CleanUp
        cartRepository.deleteById(createdCart.getId());
        userRepository.deleteById(createdUser.getId());
    }

    @Test
    public void should_add_product_to_cart() {
        //Given
        User createdUser = userRepository.save(user);
        Group group = new Group();
        product.setGroup(group);
        Product createdProduct = productRepository.save(product);
        Cart cart = new Cart(user);
        Cart createdCart = cartService.createCart(cart);
        //When
        cartService.increaseProductQuantityInCart(createdCart.getId(), createdProduct.getId(), 3L);
        //Then
        OptionalLong quantityInCart = cartRepository.findById(createdCart.getId())
                .orElseThrow(() -> new NotFoundException(
                        "testCartServiceImpl.should_add_product_to_cart: cart not found, id:  " + createdCart.getId()))
                .getItems().stream()
                .filter(p -> p.getProduct().getId().equals(createdProduct.getId()))
                .mapToLong(CartItem::getQuantity)
                .findFirst();
        Long quantityInStock = productRepository.findById(product.getId())
                .orElseThrow(() -> new NotFoundException(
                                "testCartServiceImpl.should_add_product_to_cart: product not found, id:  " +
                                        createdProduct.getId()))
                .getQuantityInStock();
        assertEquals(3L, quantityInCart.getAsLong());
        assertEquals(10L, quantityInStock);
        //CleanUp
        cleanUp(createdCart.getId(), createdProduct.getId(), createdUser.getId());
    }

    private void cleanUp(Long cartId, Long productId, Long userId) {
        cartRepository.deleteById(cartId);
        productRepository.deleteById(productId);
        userRepository.deleteById(userId);
    }
}