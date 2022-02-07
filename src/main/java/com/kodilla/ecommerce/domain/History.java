package com.kodilla.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "HISTORIES")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(targetEntity = HistoryEntry.class,
            mappedBy = "history",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<HistoryEntry> entries = new ArrayList<>();

    @Builder
    public History(User user) {
        this.user = user;
    }
}
