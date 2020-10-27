package com.kodilla.ecommerce.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime timeOfCreationRandomKey;
    private LocalDateTime signUpDate;

  //  private Cart cart;

    public User(String nickname, String name, String surname, String email, boolean isBlocked) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isBlocked = isBlocked;
        this.signUpDate = LocalDateTime.now();
    }
}


