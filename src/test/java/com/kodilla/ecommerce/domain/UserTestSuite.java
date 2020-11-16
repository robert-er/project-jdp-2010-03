package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.repository.OrderRepository;
import com.kodilla.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static com.kodilla.ecommerce.domain.OrderStatus.PAID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

        final private User user = new User(null, "John", "Kowalsky", "john@test.pl", false);

    @Test
    public void createUserTest() {
        //Given

        //When
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
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
    public void checkUserSurnameTest() {
        //Given
        String surname = "Bruznik";

        //When
        user.setSurname(surname);
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        assertEquals(user.getSurname(), surname);

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
        LocalDateTime localDateTime = LocalDateTime.of(2020, 2, 2, 2, 2);

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
        //Given
        String randomKey = "4253523523552352";

        //When
        user.setRandomKey(randomKey);
        userRepository.save(user);
        Long userLong = user.getId();

        //Then
        assertEquals(user.getRandomKey(), randomKey);

        //CleanUp
        userRepository.deleteById(userLong);
    }

    @Test
    public void checkUserOneOrderTest() {
        //Given
        Order order = new Order();
        order.setUser(user);

        //When
        userRepository.save(user);
        orderRepository.save(order);

        Long userLong = user.getId();

        //Then
        assertEquals(order.getUser().getId(), user.getId());

        //Clean-up
        userRepository.deleteById(userLong);
    }

    @Test
    public void checkUserManyOrdersTest() {
        //Given
        OrderStatus status = PAID;

        Order order1 = new Order();
        order1.setUser(user);
        order1.setStatus(status);

        Order order2 = new Order();
        order2.setUser(user);
        order2.setStatus(status);

        Order order3 = new Order();
        order3.setUser(user);
        order3.setStatus(status);

        //When
        userRepository.save(user);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        Long userLong = user.getId();

        //Then
        assertEquals(order1.getUser().getId(), user.getId());
        assertEquals(order2.getUser().getId(), user.getId());
        assertEquals(order3.getUser().getId(), user.getId());

        //Clean-up
        userRepository.deleteById(userLong);
    }
}
