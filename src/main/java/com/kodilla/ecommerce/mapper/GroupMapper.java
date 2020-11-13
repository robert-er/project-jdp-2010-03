package com.kodilla.ecommerce.mapper;
import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.dto.GroupInCartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GroupMapper {

    private final ProductMapper productMapper;

    public Group mapToGroup(final GroupDto groupDto) {

        Group group = new Group();
        group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        return group;
    }

    public GroupDto mapToGroupDto(final Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        groupDto.setDescription(group.getDescription());
        groupDto.setProducts(productMapper.mapToProductDtoList(group.getProducts()));
        return groupDto;
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }

    public GroupInCartItemDto mapToGroupInCartDto(Group group) {
        GroupInCartItemDto groupInCartItemDto = new GroupInCartItemDto();
        groupInCartItemDto.setId(group.getId());
        groupInCartItemDto.setName(group.getName());
        groupInCartItemDto.setDescription(group.getDescription());
        return groupInCartItemDto;
    }


}