package com.kodilla.ecommerce.controller;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.mapper.GroupMapper;
import com.kodilla.ecommerce.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @Autowired
    private GroupService groupServiceImpl;
    @Autowired
    private GroupMapper groupMapper;

    @GetMapping(value = "getGroups")
    public List<GroupDto> getGroups() {
        return groupMapper.mapToGroupDtoList(groupServiceImpl.getAllGroups());
    }

    @GetMapping("{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) throws NotFoundException {
        return groupMapper.mapToGroupDto(groupServiceImpl.getGroup(groupId));
    }

    @PostMapping
    public void createGroup(@RequestBody GroupDto groupDto) {
        groupServiceImpl.saveGroup(groupMapper.mapToGroup(groupDto));
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return groupMapper.mapToGroupDto(groupServiceImpl.saveGroup(groupMapper.mapToGroup(groupDto)));
    }
}
