package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.History;
import com.kodilla.ecommerce.domain.HistoryEntry;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public History createHistory(History history) {
        return historyRepository.findByUserId(history.getUser().getId()).orElseGet(() -> save(history));
    }

    @Override
    public void addEntryToHistory(Long userId, HistoryEntry historyEntry) throws NotFoundException {
        History history = historyRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("No history for user id: " + userId));
        history.getEntries().add(historyEntry);
        save(history);
    }

    @Override
    public List<HistoryEntry> getHistory(Long userId) throws NotFoundException {
        return historyRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("No history for user id: " + userId))
                .getEntries();
    }

    private History save(History history) {
        return historyRepository.save(history);
    }
}
