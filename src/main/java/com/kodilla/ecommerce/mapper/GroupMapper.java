package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.GroupDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    public Group mapToGroup(final GroupDto groupDto) {

        Group group = new Group();
        group.getName();
        group.getDescription();
        group.setProducts(new ArrayList<>());
        return group;
    }


    public GroupDto mapToGroupDto(final Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.getName();
        groupDto.getDescription();
        groupDto.setProducts(new ArrayList<>());
        return groupDto;
    }

//    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
//        return groupList.stream()
//                .map(t -> new GroupDto(t.getName(), t.getDescription(), t.setProducts(new ArrayList<>())
//                .collect(Collectors.toList());
//    }

}

