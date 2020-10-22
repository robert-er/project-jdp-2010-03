package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.group.Group;
import com.kodilla.ecommerce.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroupById(final Long id) {
        return groupRepository.findById(id);
    }

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }
}
