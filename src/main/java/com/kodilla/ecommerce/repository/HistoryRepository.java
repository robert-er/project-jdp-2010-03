package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Override
    List<History> findAll();

    @Override
    Optional<History> findById(Long id);

    @Override
    <S extends History> S save(S history);

    @Override
    void deleteById(Long id);

    void deleteByUserId(Long userId);

    Optional<History> findByUserId(Long userId);
}
