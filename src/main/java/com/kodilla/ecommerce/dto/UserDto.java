package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.UserRandomKey;
import lombok.Data;


@Data
public class UserDto {

    private Long userId;
    private String userName;
    private boolean isBlocked = true;
    private UserRandomKey userRandomKey;

    public UserDto (String userName){
        this.userName = userName;
    }

}