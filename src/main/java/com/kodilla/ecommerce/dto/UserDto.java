package com.kodilla.ecommerce.dto;


import lombok.Data;

import java.time.LocalDate;


@Data
public class UserDto {

    private Long id;
    private String nickname;
    private String name;
    private String surname;
    private String email;
    private boolean isBlocked = true;
    private String randomKey;
    private LocalDate timeOfCreationRandomKey;

    public UserDto(String nickname, String name, String surname, String email) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}