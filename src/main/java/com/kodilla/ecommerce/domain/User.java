package com.kodilla.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;

@Getter
@Setter
public class User {

    @GeneratedValue
    private Long userId;
    private String userName;
    private boolean isBlocked = true;
    private UserRandomKey userRandomKey;

    public User(String userName){
        this.userName = userName;

    }

}
