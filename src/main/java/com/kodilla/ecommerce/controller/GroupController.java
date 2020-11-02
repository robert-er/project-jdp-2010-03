package com.kodilla.ecommerce.controller;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.mapper.GroupMapper;
import com.kodilla.ecommerce.service.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @Autowired
    private GroupServiceImpl groupServiceImpl;
    @Autowired
    private GroupMapper groupMapper;

//    @GetMapping
//    public List<GroupDto> getGroups() {
//        return groupMapper.mapToGroupDtoList(groupServiceImpl.getAllGroups());
//    }

    @GetMapping("{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(groupServiceImpl.getGroup(groupId).orElseThrow(GroupNotFoundException::new));
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
