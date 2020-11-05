package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.exception.OrderNotFoundException;
import com.kodilla.ecommerce.mapper.OrderMapper;
import com.kodilla.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("{id}")
    public OrderDto getOrder(@PathVariable Long id) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.getOrderById(id).orElseThrow(OrderNotFoundException::new));
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @PostMapping
    public void addOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderMapper.mapToOrder(orderDto));
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Long id) throws OrderNotFoundException {
        orderService.getOrderById(id).orElseThrow(OrderNotFoundException::new);
        orderService.deleteById(id);
    }

    @PutMapping("{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.updateOrderById(id, orderMapper.mapToOrder(orderDto)).orElseThrow(OrderNotFoundException::new));
    }
}
