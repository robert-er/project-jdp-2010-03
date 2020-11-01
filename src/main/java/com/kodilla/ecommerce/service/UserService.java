package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.exception.UserAlreadyBlocked;
import com.kodilla.ecommerce.exception.UserIsNotBlocked;
import com.kodilla.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public User getUserById(final Long id) throws NotFoundException{
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
        throw new NotFoundException("User not found");
        }
    }

    public User createUser(final User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setSignUpDate(now);
        return userRepository.save(user);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void blockUser(final Long id) throws UserAlreadyBlocked {
        if (userService.getUserById(id).isBlocked()) {
            throw new UserAlreadyBlocked("User already blocked");
        } else {
            User user = userRepository.findById(id).get();
            user.setBlocked(true);
            saveUser(user);
        }
    }

    public String generateRandomKey(final Long id) {
        User user = getUserById(id);
        LocalDateTime now = LocalDateTime.now();
        if (user.getTimeOfCreationRandomKey() == null || LocalDateTimeDiffLessThanHour(user.getTimeOfCreationRandomKey(), now)) {
            String key = String.valueOf(ThreadLocalRandom.current().nextInt(0, 4000000));
            // Można pomyśleć o dodaniu hashcode'a dla większego randomu
            user.setRandomKey(key);
            user.setTimeOfCreationRandomKey(now);
            saveUser(user);
            return key;
        } else {
            return user.getRandomKey();
        }
    }

    public void unblockUser(final Long id, final String generatedKey) throws NotFoundException, UserIsNotBlocked {
        if (userService.getUserById(id).isBlocked()) {
            User user = userRepository.findById(id).get();
            if (user.getRandomKey().equals(generatedKey)) {
            user.setBlocked(false);
            saveUser(user);
            } else {
                throw new NotFoundException("The key does not match original value, please contact IT");
            }
        } else {
            throw new UserIsNotBlocked("User is not blocked");
        }
    }

    public boolean LocalDateTimeDiffLessThanHour(LocalDateTime last, LocalDateTime now) {
        if (Duration.between(last, now).toSeconds() > 3600) {
            return true;
        } else {
            return false;
        }
    }

}


