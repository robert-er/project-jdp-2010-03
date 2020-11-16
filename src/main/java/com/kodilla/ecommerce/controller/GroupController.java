package com.kodilla.ecommerce.controller;
import com.kodilla.ecommerce.domain.HistoryType;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.dto.HistoryEntryDto;
import com.kodilla.ecommerce.mapper.GroupMapper;
import com.kodilla.ecommerce.mapper.HistoryEntryMapper;
import com.kodilla.ecommerce.service.GroupService;
import com.kodilla.ecommerce.service.HistoryService;
import com.kodilla.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/group")
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;
    private final UserService userService;
    private final HistoryService historyService;
    private final HistoryEntryMapper historyEntryMapper;

    @GetMapping
    public List<GroupDto> getGroups() {
        return groupMapper.mapToGroupDtoList(groupService.getAllGroups());
    }

    @GetMapping("{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) {
        return groupMapper.mapToGroupDto(groupService.getGroup(groupId));
    }

    @PostMapping
    public void createGroup(@RequestBody GroupDto groupDto,
                            @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        groupService.saveGroup(groupMapper.mapToGroup(groupDto));
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.GROUP, "createGroup");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto groupDto,
                                @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.GROUP, "updateGroup");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
        return groupMapper.mapToGroupDto(groupService.updateGroup(groupMapper.mapToGroup(groupDto)));

    }
}
