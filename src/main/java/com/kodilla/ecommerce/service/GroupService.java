package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    public List<Group> getAllGroups() {
        return repository.findAll();
    }

    public Group getGroup(final Long id) throws NotFoundException {
        Optional<Group> group = repository.findById(id);

        if (group.isPresent()) {
            return group.get();
        } else {
            throw new NotFoundException("Group not found");
        }
    }

    public Group saveGroup(final Group group) {
        return repository.save(group);
    }


}

