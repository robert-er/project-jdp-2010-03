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

    private final User user = new User();
    private final Cart cart1 = new Cart(user);

    @Test
    void shouldGetId() {
        //Given
        user.setName("John");
        user.setSurname("Smith");
        user.setEmail("Shimt.jogn@mailc.com");
        userRepository.save(user);
        Long idCartBeforeSaving = cart1.getId();

        //When
        cartRepository.save(cart1);
        Long retrievedIdAfterSaving = cart1.getId();

        //Then
        assertNull(idCartBeforeSaving);
        assertNotNull(retrievedIdAfterSaving);

        //CleanUp
        cartRepository.delete(cart1);
        userRepository.delete(user);

    }

    @Test
    void shouldGetUser() {
        //Given
        user.setName("John");
        user.setSurname("Smith");
        user.setEmail("Shimt.jogn@mailc.com");
        userRepository.save(user);
        Cart cartSaved = cartRepository.save(cart1);

        //When
        User retrievedUser = cartSaved.getUser();

        //Then
        assertEquals(user,retrievedUser);

        //CleanUp
        cartRepository.delete(cart1);
        userRepository.delete(user);
    }

    @Test
    void shouldGetItems() {
        //Given
        user.setName("John");
        user.setSurname("Smith");
        user.setEmail("Shimt.jogn@mailc.com");
        user.setRandomKey(user.getRandomKey());
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
        cartItem1.setCart(cart1);

        CartItem cartItem2 = new CartItem();
        cartItem2.setProduct(product2);
        cartItem2.setQuantity(2L);
        cartItem2.setCart(cart1);

        List<CartItem> items = new ArrayList<>();
        items.add(cartItem1);
        items.add(cartItem2);

        //When
        cart1.setItems(items);
        Cart cartSaved = cartRepository.save(cart1);
        int numberOfCartItems = cartSaved.getItems().size();

        //Then
        assertEquals(2, numberOfCartItems);

        //CleanUp
        cartRepository.delete(cart1);
        userRepository.delete(user);
    }
}

