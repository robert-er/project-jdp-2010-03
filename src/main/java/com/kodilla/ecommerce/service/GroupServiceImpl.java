package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.GroupRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository;

    @Override
    public List<Group> getAllGroups() {
        return repository.findAll();
    }

    @Override
    public Group getGroup(final Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(()
                -> new NotFoundException("Group not found"));
    }

    @Override
    public Group saveGroup(final Group group) throws NotFoundException {
        Long id = group.getId();
        Optional<Group> idNumber = repository.findById(id);

        if (idNumber.isPresent()) {
            throw new NotFoundException("The group already exists!");
        }
        return repository.save(group);
    }

    @Override
    public Group updateGroup(final Group group) throws NotFoundException {
        Long id = group.getId();
        Optional<Group> idNumber = repository.findById(id);

        if (idNumber.isEmpty()) {
            throw new NotFoundException("The group does not exist!");
        }
        return repository.save(group);
    }
}

