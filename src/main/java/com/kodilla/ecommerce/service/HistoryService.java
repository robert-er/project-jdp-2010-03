package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.History;
import com.kodilla.ecommerce.domain.HistoryEntry;
import com.kodilla.ecommerce.exception.NotFoundException;

import java.util.List;

public interface HistoryService {

    History createHistory(History history);
    void addEntryToHistory(Long userId, HistoryEntry historyEntry) throws NotFoundException;
    List<HistoryEntry> getHistory(Long userId) throws NotFoundException;
}
