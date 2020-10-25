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
    public void createUser(@RequestParam String nickname,@RequestParam String name, @RequestParam String surname,@RequestParam String email){

    }

    @RequestMapping(method = RequestMethod.PUT, value = "blockUser")
    public void blockUser (@RequestParam Long id){

    }

    @RequestMapping(method = RequestMethod.PUT, value = "generateRandomKey")
    public String generateRandomKey(@RequestParam Long id){
        return "";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "unblockUser")
    public void unblockUser(@RequestParam Long id, @RequestParam String generatedKey){

    }
}
