package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Override
    List<Group> findAll();

    @Override
    Optional<Group> findById(Long id);

    @Override
    <S extends Group> S save(S group);
}
