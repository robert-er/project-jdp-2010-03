package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.HistoryEntry;
import com.kodilla.ecommerce.dto.HistoryEntryDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class HistoryEntryMapper {

    private final HistoryRepository historyRepository;

    public HistoryEntry mapToHistoryEntry(HistoryEntryDto historyEntryDto, Long userId) {
        HistoryEntry historyEntry = new HistoryEntry();
        historyEntry.setHistory(historyRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("History for User id: " + userId + " not found")));
        historyEntry.setDate(historyEntryDto.getDate());
        historyEntry.setHistoryType(historyEntryDto.getHistoryType());
        historyEntry.setDetails(historyEntryDto.getDetails());
        return historyEntry;
    }

    public HistoryEntryDto mapToHistoryEntryDto(HistoryEntry historyEntry) {
        HistoryEntryDto historyEntryDto = new HistoryEntryDto();
        historyEntryDto.setId(historyEntry.getId());
        historyEntryDto.setHistoryId(historyEntry.getHistory().getId());
        historyEntryDto.setDate(historyEntry.getDate());
        historyEntryDto.setHistoryType(historyEntry.getHistoryType());
        historyEntryDto.setDetails(historyEntry.getDetails());
        return historyEntryDto;
    }

    public List<HistoryEntryDto> mapToHistoryEntryDtoList(List<HistoryEntry> historyEntries) {
        return historyEntries.stream()
                .map(this::mapToHistoryEntryDto)
                .collect(Collectors.toList());
    }
}
