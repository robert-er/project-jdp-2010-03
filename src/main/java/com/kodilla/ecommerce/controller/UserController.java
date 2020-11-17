package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.HistoryType;
import com.kodilla.ecommerce.dto.HistoryDto;
import com.kodilla.ecommerce.dto.HistoryEntryDto;
import com.kodilla.ecommerce.dto.UserDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.mapper.HistoryEntryMapper;
import com.kodilla.ecommerce.mapper.HistoryMapper;
import com.kodilla.ecommerce.mapper.UserMapper;
import com.kodilla.ecommerce.repository.UserRepository;
import com.kodilla.ecommerce.service.HistoryService;
import com.kodilla.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final HistoryService historyService;
    private final HistoryEntryMapper historyEntryMapper;
    private final HistoryMapper historyMapper;
    private final UserRepository userRepository;

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) throws NotFoundException {
        userService.createUser(userMapper.mapToUser(userDto));
        Long userId = userRepository.findByNameAndSurnameAndEmail(userDto.getName(),
                        userDto.getSurname(),
                        userDto.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found. Name: " + userDto.getName() +
                        " , Surname: " + userDto.getSurname() + " , Email " + userDto.getEmail()))
                .getId();
        historyService.createHistory(historyMapper.mapToHistory(new HistoryDto(userId)));
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.USER, "createUser");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
    }

    @PutMapping("block/{id}")
    public void blockUser (@PathVariable Long id, @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        userService.blockUser(id, key);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.USER, "blockUser");
        historyService.addEntryToHistory(id, historyEntryMapper.mapToHistoryEntry(historyEntryDto, id));
    }

    @PutMapping("key")
    public String generateRandomKey(@RequestParam Long id){
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.USER, "generateRandomKey");
        historyService.addEntryToHistory(id, historyEntryMapper.mapToHistoryEntry(historyEntryDto, id));
        return userService.generateRandomKey(id);
    }

    @PutMapping("unblock/{id}")
    public void unblockUser(@PathVariable Long id, @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        userService.unblockUser(id, key);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.USER, "unblockUser");
        historyService.addEntryToHistory(id, historyEntryMapper.mapToHistoryEntry(historyEntryDto, id));
    }

    @GetMapping("history/{id}")
    public List<HistoryEntryDto> getHistory(@PathVariable Long id) {
        return historyEntryMapper.mapToHistoryEntryDtoList(historyService.getHistory(id));
    }
}
