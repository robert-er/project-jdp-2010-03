package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartTest {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    private final User user = new User("JohnnyTheSinner", "John",
            "Smith", "ohjohnny@gmail.com", false);
    private final Cart cart = new Cart(user);


    @Test
    void shouldGetId() {
        //Given
        userRepository.save(user);
        Long idCartBeforeSaving = cart.getId();

        //When
        cartRepository.save(cart);
        Long retrievedIdAfterSaving = cart.getId();

        //Then
        assertNull(idCartBeforeSaving);
        assertNotNull(retrievedIdAfterSaving);

        //CleanUp
        cartRepository.delete(cart);
        userRepository.delete(user);
    }

    @Test
    void shouldGetUser() {
        //Given
        userRepository.save(user);
        Cart cartSaved = cartRepository.save(cart);

        //When
        User retrievedUser = cartSaved.getUser();

        //Then
        assertEquals(user, retrievedUser);

        //CleanUp
        cartRepository.delete(cart);
        userRepository.delete(user);
    }

    @Test
    void shouldGetItems() {
        //Given
        userRepository.save(user);
        Group group1 = new Group();

        Product product1 = new Product();
        product1.setTitle("title1");
        product1.setDescription("description1");
        product1.setGroup(group1);

        Product product2 = new Product();
        product2.setTitle("title2");
        product2.setDescription("description2");
        product2.setGroup(group1);

        CartItem cartItem1 = new CartItem();
        cartItem1.setProduct(product1);
        cartItem1.setQuantity(1L);
        cartItem1.setCart(cart);

        CartItem cartItem2 = new CartItem();
        cartItem2.setProduct(product2);
        cartItem2.setQuantity(2L);
        cartItem2.setCart(cart);

        List<CartItem> items = new ArrayList<>();
        items.add(0,cartItem1);
        items.add(1,cartItem2);

        //When
        cart.setItems(items);
        Cart cartSaved = cartRepository.save(cart);

        //Then
        assertEquals(cartItem1, cartSaved.getItems().get(0));
        assertEquals(cartItem2, cartSaved.getItems().get(1));

        //CleanUp
        cartRepository.delete(cart);
        userRepository.delete(user);
        productRepository.delete(product1);
        productRepository.delete(product2);
    }

    @Test
    void shouldAddItemCartInDBWhenSavingCart() {
        //Given
        Group group1 = new Group();
        userRepository.save(user);

        Product product1 = new Product();
        product1.setTitle("title1");
        product1.setDescription("description1");
        product1.setGroup(group1);

        CartItem cartItem1 = new CartItem();
        cartItem1.setProduct(product1);
        cartItem1.setQuantity(1L);
        cartItem1.setCart(cart);
        List<CartItem> items = new ArrayList<>();
        items.add(cartItem1);
        cart.setItems(items);

        //When
        cartRepository.save(cart);
        CartItem retrievedCartItem = cartItemRepository.findByCartAndProduct(cart, product1).get();

        //Then
        assertNotNull(retrievedCartItem);

        //CleanUp
        cartRepository.delete(cart);
        userRepository.delete(user);
        productRepository.delete(product1);
    }

    @Test
    void shouldDeleteCartItemFromDBWhenDeletingCart() {
        //Given
        Group group1 = new Group();
        userRepository.save(user);

        Product product1 = new Product();
        product1.setTitle("title1");
        product1.setDescription("description1");
        product1.setGroup(group1);

        CartItem cartItem1 = new CartItem();
        cartItem1.setProduct(product1);
        cartItem1.setQuantity(1L);
        cartItem1.setCart(cart);

        List<CartItem> items = new ArrayList<>();
        items.add(cartItem1);
        cart.setItems(items);
        cartRepository.save(cart);
        CartItem retrievedCartItemBeforeDeleting = cartItemRepository.findByCartAndProduct(cart, product1).get();

        //When
        cartRepository.delete(cart);

        //Then
        assertNotNull(retrievedCartItemBeforeDeleting);
        assertTrue(cartItemRepository.findByCartAndProduct(cart, product1).isEmpty());

        //CleanUp
        userRepository.delete(user);
        productRepository.delete(product1);
    }
}

