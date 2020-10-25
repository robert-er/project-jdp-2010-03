package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public UserDto createUser(@RequestParam String nickname,@RequestParam String name, @RequestParam String surname,@RequestParam String email){
        return new UserDto("Johnny","John", "Smith","smith@smith.com");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "blockUser")
    public UserDto blockUser (@RequestParam Long id){
        return new UserDto("Johnny","John", "Smith","smith@smith.com");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "generateRandomKey")
    public UserDto generateRandomKey(@RequestParam Long id){
        return new UserDto("Johnny","John", "Smith","smith@smith.com");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "unblockUser")
    public UserDto unblockUser(@RequestParam Long id){
        return new UserDto("Johnny","John", "Smith","smith@smith.com");
    }
}
