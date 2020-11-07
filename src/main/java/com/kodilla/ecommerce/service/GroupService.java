package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository repository;

    public List<Group> getAllGroups() {
        return repository.findAll();
    }

    public Group getGroup(final Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(()
                -> new NotFoundException("Group not found"));
    }

    public Group saveGroup(final Group group) throws NotFoundException {

        Long id = group.getId();
        Optional groupId = repository.findById(id);

        if (groupId.isPresent()) {
            throw new NotFoundException("The group already exists!");
        } else {

        } return repository.save(group);

    }

    public Group updateGroup(final Group group) {
        return repository.save(group);
    }

}

