package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.repository.UserRepository;
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
        //userRepository.deleteById(userLong);

    }
}
