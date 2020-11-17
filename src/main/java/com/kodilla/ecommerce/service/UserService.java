package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.User;

import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.exception.UserAlreadyBlockedException;
import com.kodilla.ecommerce.exception.UserIsNotBlockedException;

public interface UserService {

    User getUserById(final Long id);
    User createUser(final User user);
    void blockUser(final Long id, String generatedKey) throws UserAlreadyBlockedException, NotFoundException;
    String generateRandomKey(final Long id);
    void unblockUser(final Long id, final String generatedKey) throws NotFoundException, UserIsNotBlockedException;
    boolean validateGeneratedKey(Long id, String generatedKey);
}
