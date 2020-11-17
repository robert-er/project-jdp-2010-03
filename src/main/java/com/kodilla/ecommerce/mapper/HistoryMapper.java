package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.History;
import com.kodilla.ecommerce.dto.HistoryDto;
import com.kodilla.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class HistoryMapper {

    private final UserService userService;

    public History mapToHistory(HistoryDto historyDto) {
        History history = new History();
        history.setId(historyDto.getId());
        history.setUser(userService.getUserById(historyDto.getUserId()));
        history.setEntries(new ArrayList<>());
        return history;
    }
}
