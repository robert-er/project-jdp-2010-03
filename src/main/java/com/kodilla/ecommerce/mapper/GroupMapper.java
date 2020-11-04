package com.kodilla.ecommerce.mapper;
import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.dto.GroupDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    public Group mapToGroup(final GroupDto groupDto) {

        Group group = new Group();
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
//        group.setProducts(groupDto.getProducts());
        return group;
    }

    public GroupDto mapToGroupDto(final Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setName(group.getName());
        groupDto.setDescription(group.getDescription());
//        groupDto.setProducts(group.getProducts());
        return groupDto;
    }
}

//   public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
//        return groupList.stream()
//                 .map(t -> new GroupDto(t.getName(), t.getDescription(), t.getProducts()))
//                        .collect(Collectors.toList());
//    }

//}

