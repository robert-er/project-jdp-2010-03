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

        return new Group(
                groupDto.getName(),
                groupDto.getDescription(),
                groupDto.setProducts(new ArrayList<>());
    }


    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(
                group.getName(),
                group.getDescription(),
                group.setProducts(new ArrayList<>());
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupsList) {
        return groupsList.stream()
                .map(t -> new GroupDto(t.getName(), t.getDescription(), t.setProducts(new ArrayList<>())
                       .collect(Collectors.toList());
    }

}
