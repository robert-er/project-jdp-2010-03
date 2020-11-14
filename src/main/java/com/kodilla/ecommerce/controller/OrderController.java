package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.mapper.OrderMapper;
import com.kodilla.ecommerce.service.OrderService;
import com.kodilla.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final UserService userService;

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
                         @RequestParam Long userId, @RequestParam String generatedKey) {
        userService.validateGeneratedKey(userId, generatedKey);
        orderService.saveOrder(orderMapper.mapToOrder(orderDto));
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Long id,
                            @RequestParam Long userId, @RequestParam String generatedKey) {
        userService.validateGeneratedKey(userId, generatedKey);
        orderService.deleteById(id);
    }

    @PutMapping("{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto,
                                @RequestParam Long userId, @RequestParam String generatedKey) {
        userService.validateGeneratedKey(userId, generatedKey);
        Order orderToUpdate = orderMapper.mapToOrder(orderDto);
        Order updatedOrder = orderService.updateOrderById(id, orderToUpdate);
        return orderMapper.mapToOrderDto(updatedOrder);
    }
}
