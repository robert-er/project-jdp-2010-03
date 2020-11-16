package com.kodilla.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryDto {

    private Long id;
    private Long userId;
    private List<HistoryEntryDto> entries;

    public HistoryDto(Long userId) {
        this.userId = userId;
    }
}
