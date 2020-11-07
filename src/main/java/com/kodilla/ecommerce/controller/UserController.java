package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.UserDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.exception.UserAlreadyBlockedException;
import com.kodilla.ecommerce.exception.UserIsNotBlockedException;
import com.kodilla.ecommerce.mapper.UserMapper;
import com.kodilla.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public void createUser(@RequestBody UserDto userDto){
        userService.createUser(userMapper.mapToUser(userDto));
    }

    @PutMapping("block")
    public void blockUser (@RequestParam Long id) throws UserAlreadyBlockedException {
        userService.blockUser(id);
    }

    @PutMapping("key")
    public String generateRandomKey(@RequestParam Long id){
        return userService.generateRandomKey(id);
    }

    @PutMapping("unblock")
    public void unblockUser(@RequestParam Long id, @RequestParam String generatedKey) throws NotFoundException, UserIsNotBlockedException {
        userService.unblockUser(id, generatedKey);
    }
}
