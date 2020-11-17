package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.UserDto;
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

    @PutMapping("block/{id}")
    public void blockUser (@PathVariable Long id, @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        userService.blockUser(id, key);
    }

    @PutMapping("key")
    public String generateRandomKey(@RequestParam Long id){
        return userService.generateRandomKey(id);
    }

    @PutMapping("unblock/{id}")
    public void unblockUser(@PathVariable Long id, @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        userService.unblockUser(id, key);
    }
}
