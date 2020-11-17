package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.UserDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.HistoryRepository;
import com.kodilla.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private final Random random = new Random();
    private final String nickname = "nickname";
    private final String name = "name";
    private final String surname = "surname";
    private final String email = "email@example.com";

    @Test
    public void shouldCreateUser() {
        //Given
        String generatedName = name + random.nextInt(1000);
        String generatedSurname = surname + random.nextInt(1000);
        //When
        Long userId = generateUser(generatedName, generatedSurname, false);
        //Then
        assertNotEquals(null, userId);
        //CleanUp
        removeUser(userId);
    }

    @Test
    public void shouldBlockUser() {
        //Given
        String generatedName = name + random.nextInt(1000);
        String generatedSurname = surname + random.nextInt(1000);
        Long userId = generateUser(generatedName, generatedSurname, false);
        String randomKey = generateRandomKey(userId, generatedName, generatedSurname);
        //When
        HttpEntity<UserDto> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = restTemplate
                .exchange(createURLWithPort("block/" + userId + "?userId=" + userId + "&key=" + randomKey),
                HttpMethod.PUT, entity, String.class);
        boolean isBlocked = userRepository.findByNameAndSurnameAndEmail(generatedName, generatedSurname, email)
                .orElseThrow(() -> new NotFoundException("User not found by name: " + generatedName
                        + ", surname: " + surname + ", email: " + email))
                .isBlocked();
        //Then
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(isBlocked);
        //CleanUp
        removeUser(userId);
    }

    @Test
    public void shouldGenerateRandomKey() {
        //Given
        String generatedName = name + random.nextInt(1000);
        String generatedSurname = surname + random.nextInt(1000);
        Long userId = generateUser(generatedName, generatedSurname, false);
        //When
        String randomKey = generateRandomKey(userId, generatedName, generatedSurname);
        //Then
        assertNotNull(randomKey);
        //CleanUp
        removeUser(userId);
    }

    @Test
    public void shouldUnblockUser() {
        //Given
        String generatedName = name + random.nextInt(1000);
        String generatedSurname = surname + random.nextInt(1000);
        Long userId = generateUser(generatedName, generatedSurname, true);
        String randomKey = generateRandomKey(userId, generatedName, generatedSurname);
        //When
        HttpEntity<UserDto> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = restTemplate
                .exchange(createURLWithPort("unblock/" + userId + "?userId=" + userId + "&key=" + randomKey),
                HttpMethod.PUT, entity, String.class);
        boolean isBlocked = userRepository.findByNameAndSurnameAndEmail(generatedName, generatedSurname, email)
                .orElseThrow(() -> new NotFoundException("User not found by name: " + generatedName
                        + ", surname: " + surname + ", email: " + email))
                .isBlocked();
        //Then
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(isBlocked);
        //CleanUp
        removeUser(userId);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/v1/user/" + uri;
    }

    private Long generateUser(String name, String surname, boolean isBlocked) {
        UserDto userDto = new UserDto(nickname, name, surname, email, isBlocked);
        HttpEntity<UserDto> entity = new HttpEntity<>(userDto, new HttpHeaders());
        restTemplate.exchange(createURLWithPort(""),
                HttpMethod.POST, entity, String.class);
        return userRepository.findByNameAndSurnameAndEmail(name, surname, email)
                .orElseThrow(() -> new NotFoundException("User not found by name: " + name
                        + ", surname: " + surname + ", email: " + email))
                .getId();
    }

    private String generateRandomKey(Long userId, String name, String surname) {
        HttpEntity<UserDto> entity = new HttpEntity<>(null, new HttpHeaders());
        restTemplate.exchange(createURLWithPort("key?id=" + userId),
                HttpMethod.PUT, entity, String.class);
       return userRepository.findByNameAndSurnameAndEmail(name, surname, email)
                .orElseThrow(() -> new NotFoundException("User not found by name: " + name
                        + ", surname: " + surname + ", email: " + email))
                .getRandomKey();
    }

    private void removeUser(Long id) {
        historyRepository.deleteByUserId(id);
        userRepository.deleteById(id);
    }
}