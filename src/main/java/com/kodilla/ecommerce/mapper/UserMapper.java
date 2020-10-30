package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(userDto.getNickname(), userDto.getName(), userDto.getSurname(),
                userDto.getEmail(), userDto.isBlocked());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(user.getNickname(), user.getName(), user.getSurname(),
                user.getEmail(), user.isBlocked());
    }
}
