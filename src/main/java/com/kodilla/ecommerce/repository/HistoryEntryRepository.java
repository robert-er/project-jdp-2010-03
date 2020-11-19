package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.HistoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface HistoryEntryRepository extends JpaRepository<HistoryEntry, Long> {

    @Override
    List<HistoryEntry> findAll();

    @Override
    Optional<HistoryEntry> findById(Long id);

    @Override
    <S extends HistoryEntry> S save(S historyEntry);

    @Override
    void deleteById(Long id);
}
