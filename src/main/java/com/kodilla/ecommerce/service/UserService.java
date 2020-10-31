package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.exception.UserAlreadyBlocked;
import com.kodilla.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void blockUser(final Long id) throws UserAlreadyBlocked {
        if (userService.getUserById(id).isBlocked()) {
            throw new UserAlreadyBlocked("User already blocked");
        } else {
            User user = userRepository.findById(id).get();
            user.setBlocked(true);
            userRepository.save(user);
        }
    }

    public String generateRandomKey(final Long id) {
        User user = getUserById(id);
        String key = String.valueOf(user.hashCode());
        return key;
    }

    public void unblockUser(final Long id, final String generatedKey) {

    }


}


