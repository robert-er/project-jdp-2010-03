package com.kodilla.ecommerce.controller;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.mapper.GroupMapper;
import com.kodilla.ecommerce.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/group")
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @GetMapping(value = "getGroups")
    public List<GroupDto> getGroups() {
        return groupMapper.mapToGroupDtoList(groupService.getAllGroups());
    }

    @GetMapping("{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) {
        return groupMapper.mapToGroupDto(groupService.getGroup(groupId));
    }

    @PostMapping
    public void createGroup(@RequestBody GroupDto groupDto) {
        groupService.saveGroup(groupMapper.mapToGroup(groupDto));
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return groupMapper.mapToGroupDto(groupService.saveGroup(groupMapper.mapToGroup(groupDto)));
    }
}
