package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.group.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Override
    List<Group> findAll();
    @Override
    Optional<Group> findById(Long id);
    @Override
    Group save(Group group);

}
