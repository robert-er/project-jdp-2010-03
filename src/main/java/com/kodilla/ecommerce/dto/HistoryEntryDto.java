package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.HistoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryEntryDto {

    Long id;
    Long historyId;
    LocalDateTime date;
    HistoryType historyType;
    String details;

    public HistoryEntryDto(LocalDateTime date, HistoryType historyType, String details) {
        this.date = date;
        this.historyType = historyType;
        this.details = details;
    }
}
