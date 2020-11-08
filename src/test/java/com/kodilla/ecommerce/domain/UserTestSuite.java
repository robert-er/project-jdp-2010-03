package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.repository.UserRepository;
import com.kodilla.ecommerce.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private final User user = new User();
    private final User user2 = new User("Janek", "Jan", "Kowalski", "jankowalski@gmail.com", false);
    private final User user3 = new User("Ala", "Ala", "Kowalska", "alakowalski@gmail.com", false);

    @Test
    public void createUserTest() {
        //Given

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
    public void checkUserEmailTest() {
        //Given
        String mail = "przyklad@przyklad.com";
        //When
        user.setEmail(mail);
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        assertEquals(user.getEmail(), mail);

        //CleanUp
        userRepository.deleteById(userLong);
    }

    @Test
    public void checkUserIsBlockedTest() {
        //Given
        Boolean isBlocked = true;
        //When
        user.setBlocked(true);
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        assertEquals(user.isBlocked(), isBlocked);

        //CleanUp
        userRepository.deleteById(userLong);
    }

    @Test
    public void checkUserNameTest() {
        //Given
        String name = "Jonathan";
        //When
        user.setName(name);
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        assertEquals(user.getName(), name);

        //CleanUp
        userRepository.deleteById(userLong);
    }

    @Test
    public void checkUserNicknameTest() {
        //Given
        String nickName = "Jey";
        //When
        user.setNickname(nickName);
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        assertEquals(user.getNickname(), nickName);

        //CleanUp
        userRepository.deleteById(userLong);
    }

    @Test
    public void checkUserTimeOfCreationRandomKeyTest() {
        //Given
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 12, 12, 12);
        //When
        user.setTimeOfCreationRandomKey(localDateTime);
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        assertEquals(user.getTimeOfCreationRandomKey(), localDateTime);

        //CleanUp
        userRepository.deleteById(userLong);
    }

    @Test
    public void checkUserSignUpDateTest() {
        //Given
        LocalDateTime localDateTime = LocalDateTime.of(2020, 02, 2, 2, 2);
        //When
        user.setSignUpDate(localDateTime);
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        assertEquals(user.getSignUpDate(), localDateTime);

        //CleanUp
        userRepository.deleteById(userLong);
    }

    @Test
    public void checkUserRandomKeyTest() {
/*        //Given
        String randomKey = "4253523523552352";
        //When
        user.setRandomKey(randomKey);
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        assertEquals(user.getRandomKey(), randomKey);

        //CleanUp
        userRepository.deleteById(userLong);*/
    }

    @Test
    public void checkUserCartIdTest() {
     /*   //Given
        Long cartId = 2L;
        //When
        Cart cart = new Cart(user);

        Cart cartCreated = cartService.createCart(cart);

        userRepository.save(user);

        Long userLong = user.getId();
        Long cartLong = cart.getId();
*/
    }

    @Test
    public void createUserAndCartTest() {
        // check where cart_id is assigned
    }


}
