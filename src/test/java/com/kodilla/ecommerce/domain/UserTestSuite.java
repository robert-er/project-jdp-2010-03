package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.repository.UserRepository;
import com.kodilla.ecommerce.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;


    @Test
    public void createUserAndCartTest() {
        //Given
        User user = new User("Janek", "Jan", "Kowalski", "jankowalski@gmail.com", false);
        cartService.createCart(new Cart(null, user, null));
        //When
        userRepository.save(user);
        //cartRepository.save(cart);
        Long userIdLong = user.getId();
        //Long cartIdLong = cart.getId();

        //Then
        assertTrue(userRepository.findById(userIdLong).isPresent());
        //assertTrue(cartRepository.findById(cartIdLong).isPresent());

        //CleanUp
        /*userRepository.deleteById(userIdLong);
        cartRepository.deleteById(cartIdLong);*/

    }

    @Test
    public void createUserTest() {
        //Given
        User user = new User("Janek", "Jan", "Kowalski", "jankowalski@gmail.com", false);
        //When
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        System.out.println(userLong);
        assertTrue(userRepository.findById(userLong).isPresent());

        //CleanUp
        userRepository.deleteById(userLong);

    }


    @Test
    public void saveUserTest() {
        //Given
        User user = new User("Ala", "Ala", "Kowalska", "alakowalski@gmail.com", false);
        //When
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        System.out.println(userLong);
        assertTrue(userRepository.findById(userLong).isPresent());

        //CleanUp
        userRepository.deleteById(userLong);

    }

}
