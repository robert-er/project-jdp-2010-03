package com.kodilla.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "HISTORY_ENTRIES")
public class HistoryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="history_id")
    private History history;

    LocalDateTime date;
    HistoryType historyType;
    String details;

    @Builder
    public HistoryEntry(@NotNull History history, LocalDateTime date, HistoryType historyType, String details) {
        this.history = history;
        this.date = date;
        this.historyType = historyType;
        this.details = details;
    }
}
