package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, value = "createUser",consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){

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
