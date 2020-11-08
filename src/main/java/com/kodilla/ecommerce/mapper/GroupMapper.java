package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.dto.GroupInCartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupMapper {

    public GroupInCartItemDto mapToGroupInCartDto(Group group) {
        GroupInCartItemDto groupInCartItemDto = new GroupInCartItemDto();
        groupInCartItemDto.setId(group.getId());
        groupInCartItemDto.setName(group.getName());
        groupInCartItemDto.setDescription(group.getDescription());
        return groupInCartItemDto;
    }
}
