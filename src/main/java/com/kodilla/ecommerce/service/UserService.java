package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.exception.*;
import com.kodilla.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(final Long id) throws NotFoundException{
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " does not exist"));
    }

    public User createUser(final User user) throws UserAlreadyExists {
        validateUser(user);
        LocalDateTime now = LocalDateTime.now();
        user.setSignUpDate(now);
        if (!userRepository.findByEmail(user.getEmail()).isPresent()) {
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExists("User with that email already exists");
        }
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void blockUser(final Long id) throws UserAlreadyBlockedException {
        User user = getUserById(id);
        if (user.isBlocked()) {
            throw new UserAlreadyBlockedException("User already blocked");
        } else {
            user.setBlocked(true);
            saveUser(user);
        }
    }

    public String generateRandomKey(final Long id) {
        User user = getUserById(id);
        LocalDateTime now = LocalDateTime.now();
        if (user.getTimeOfCreationRandomKey() == null || localDateTimeDiffLessThanHour(user.getTimeOfCreationRandomKey(), now)) {
            String key = String.valueOf(ThreadLocalRandom.current().nextInt(0, 4000000) + user.hashCode());
            user.setRandomKey(key);
            user.setTimeOfCreationRandomKey(now);
            saveUser(user);
            return key;
        } else {
            return user.getRandomKey();
        }
    }

    public void unblockUser(final Long id, final String generatedKey) throws NotFoundException, UserIsNotBlockedException {
        User user = getUserById(id);
        if (user.isBlocked()) {
            LocalDateTime now = LocalDateTime.now();
            if (user.getRandomKey().equals(generatedKey) && !localDateTimeDiffLessThanHour(user.getTimeOfCreationRandomKey(), now) ) {
                user.setBlocked(false);
                saveUser(user);
            } else {
                throw new NotFoundException("Either Your key is wrong, or 1 hour timestamp expired");
            }
        } else {
            throw new UserIsNotBlockedException("User is not blocked");
        }
    }

    private boolean localDateTimeDiffLessThanHour(LocalDateTime last, LocalDateTime now) {
        return Duration.between(last, now).toSeconds() > 3600;
    }

    private void validateUser(User user) throws NotValidException {
        if (user.getName().isBlank() || user.getName() == null ||
        user.getSurname().isBlank() || user.getSurname() == null ||
                user.getEmail().isBlank() || user.getEmail() == null) {
            throw new NotValidException("In order to register a new user You have to provide at least Name, Surname and Email");
        }
    }
}


