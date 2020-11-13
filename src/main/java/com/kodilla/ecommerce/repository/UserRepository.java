package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long id);

    @Override
    <S extends User> S save(S user);

    Optional<User> findByNameAndSurnameAndEmail(String name, String surname, String email);

    Optional<User> findByEmail(String email);
}
