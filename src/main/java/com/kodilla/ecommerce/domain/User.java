package com.kodilla.ecommerce.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long userId;
    private String nickname;
    private String name;
    private String surname;
    private String email;
    private boolean isBlocked;
    private String randomKey;
    private LocalDate timeOfCreationRandomKey;

    public User(String nickname, String name, String surname, String email) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}


