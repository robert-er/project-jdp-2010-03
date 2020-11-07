package com.kodilla.ecommerce.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;
    private String nickname;
    private String name;
    private String surname;
    private String email;
    private boolean isBlocked;
    private String randomKey;
    private LocalDateTime timeOfCreationRandomKey;
    private LocalDateTime signUpDate;

    public UserDto(String nickname, String name, String surname, String email, boolean isBlocked) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isBlocked = isBlocked;
    }
}