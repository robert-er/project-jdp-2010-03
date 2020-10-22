package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.group.Group;
import com.kodilla.ecommerce.dto.GroupDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto) {
        return new Group(groupDto.getId(), groupDto.getName(), groupDto.getProducts());
    }

    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(group.getId(), group.getName(), group.getProducts());
    }
    public List<GroupDto> mapToTaskDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(t -> new GroupDto(t.getId(), t.getName(), t.getProducts()))
                .collect(Collectors.toList());
    }

}
