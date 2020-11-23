package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return User.builder()
                .nickname(userDto.getNickname())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .isBlocked(userDto.isBlocked())
                .build();
    }
}
