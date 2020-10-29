package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

    @GetMapping("{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return new OrderDto(1L, "test name", "test description");
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orders = new ArrayList<>();
        orders.add(new OrderDto(2L, "name1", "description1"));
        orders.add(new OrderDto(3L, "name2", "description2"));
        return orders;
    }

    @PostMapping
    public void addOrder(@RequestBody OrderDto orderDto) {
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Long id) {
    }

    @PutMapping("{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        return orderDto;
    }
}
