package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.exception.NotFoundException;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();
    Group getGroup(final Long id) throws NotFoundException;
    Group saveGroup(final Group group) throws NotFoundException;
    Group updateGroup(final Group group) throws NotFoundException;

}
