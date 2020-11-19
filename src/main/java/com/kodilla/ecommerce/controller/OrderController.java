package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.HistoryType;
import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.dto.HistoryEntryDto;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.mapper.HistoryEntryMapper;
import com.kodilla.ecommerce.mapper.OrderMapper;
import com.kodilla.ecommerce.service.HistoryService;
import com.kodilla.ecommerce.service.OrderService;
import com.kodilla.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final HistoryService historyService;
    private final HistoryEntryMapper historyEntryMapper;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return orderMapper.mapToOrderDto(orderService.getOrderById(id));
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @PostMapping
    public void addOrder(@RequestBody OrderDto orderDto,
                         @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        orderService.createOrderWithoutCart(orderDto);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.ORDER, "addOrder");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Long id,
                            @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        orderService.deleteById(id);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.ORDER, "deleteOrder");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
    }

    @PutMapping("{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto,
                                @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        Order updatedOrder = orderService.updateOrderById(id, orderDto);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.ORDER, "updateOrder");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
        return orderMapper.mapToOrderDto(updatedOrder);
    }
}
