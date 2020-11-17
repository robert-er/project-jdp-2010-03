package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.exception.*;
import com.kodilla.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;


@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(final Long id) throws NotFoundException{
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " does not exist"));
    }

    @Override
    public User createUser(final User user) throws UserAlreadyExists {
        validateUser(user);
        LocalDateTime now = LocalDateTime.now();
        user.setSignUpDate(now);
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExists("User with that email already exists");
        }
    }

    @Override
    public void blockUser(final Long id, String generatedKey) throws UserAlreadyBlockedException {
        User user = getUserById(id);
        if (user.isBlocked()) {
            throw new UserAlreadyBlockedException("User already blocked");
        } else {
            user.setBlocked(true);
            saveUser(user);
        }
    }

    @Override
    public String generateRandomKey(final Long id) {
        User user = getUserById(id);
        LocalDateTime now = LocalDateTime.now();
        if (user.getTimeOfCreationRandomKey() == null ||
                localDateTimeDiffLessThanHour(user.getTimeOfCreationRandomKey(), now)) {
            String key = String
                    .valueOf(Math.abs(ThreadLocalRandom.current().nextInt(0, 4000000) + user.hashCode()));
            user.setRandomKey(key);
            user.setTimeOfCreationRandomKey(now);
            saveUser(user);
            return key;
        } else {
            return user.getRandomKey();
        }
    }

    @Override
    public void unblockUser(final Long id, final String generatedKey) throws UserIsNotBlockedException {
        User user = getUserById(id);
        if (user.isBlocked()) {
            user.setBlocked(false);
            saveUser(user);
        } else {
            throw new UserIsNotBlockedException("User is not blocked");
        }
    }

    @Override
    public boolean validateGeneratedKey(Long id, String key) throws NotFoundException {
        User user = getUserById(id);
        if(user.getRandomKey() != null && user.getRandomKey().equals(key)
                && !localDateTimeDiffLessThanHour(user.getTimeOfCreationRandomKey(), LocalDateTime.now())) {
            return true;
        } else {
            throw new NotFoundException("Either Your key is wrong, or 1 hour timestamp expired");
        }
    }

    private void validateUser(User user) throws NotValidException {
        if (user.getName() == null || user.getSurname() == null || user.getEmail() == null ||
                user.getName().isBlank() || user.getSurname().isBlank() || user.getEmail().isBlank()) {
            throw new NotValidException("In order to register a new user You have to provide " +
                    "at least Name, Surname and Email");
        }
    }

    private User saveUser(final User user) {
        return userRepository.save(user);
    }

    private boolean localDateTimeDiffLessThanHour(LocalDateTime last, LocalDateTime now) {
        return Duration.between(last, now).toSeconds() > 3600;
    }
}


