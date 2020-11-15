package com.kodilla.ecommerce.controller;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.mapper.GroupMapper;
import com.kodilla.ecommerce.service.GroupService;
import com.kodilla.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/group")
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;
    private final UserService userService;

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
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto groupDto,
                                @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        return groupMapper.mapToGroupDto(groupService.updateGroup(groupMapper.mapToGroup(groupDto)));
    }
}
