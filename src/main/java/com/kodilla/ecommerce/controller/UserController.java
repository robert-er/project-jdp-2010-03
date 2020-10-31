package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.dto.UserDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.exception.UserAlreadyBlocked;
import com.kodilla.ecommerce.mapper.UserMapper;
import com.kodilla.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createUser",consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
         userService.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "blockUser")
    public void blockUser (@RequestParam Long id) throws UserAlreadyBlocked {
        userService.blockUser(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "generateRandomKey")
    public String generateRandomKey(@RequestParam Long id){
        return userService.generateRandomKey(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "unblockUser")
    public void unblockUser(@RequestParam Long id, @RequestParam String generatedKey) throws NotFoundException {
        userService.unblockUser(id, generatedKey);
    }
}
