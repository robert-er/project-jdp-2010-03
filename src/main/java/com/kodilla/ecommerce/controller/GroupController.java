package com.kodilla.ecommerce.controller;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.domain.group.GroupNotFoundException;
import com.kodilla.ecommerce.mapper.GroupMapper;
import com.kodilla.ecommerce.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getGroups() {
        GroupDto groupDto1 = new GroupDto(3,"example1", null);
        GroupDto groupDto2 = new GroupDto(4,"example2", null);
        List<GroupDto> groupDtoArrayList = new ArrayList<>();
        groupDtoArrayList.add(groupDto1);
        groupDtoArrayList.add(groupDto2);
        return groupDtoArrayList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(@RequestParam Long groupId) throws GroupNotFoundException {
        return new GroupDto(3,"example", null) ;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto(6, "updated DTO", null);
    }
}
