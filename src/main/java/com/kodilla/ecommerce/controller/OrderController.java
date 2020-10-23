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
        return new OrderDto("test name", "test description");
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orders = new ArrayList<>();
        orders.add(new OrderDto("name1", "description1"));
        orders.add(new OrderDto("name2", "description2"));
        return orders;
    }

    @PostMapping
    public Long addOrder(@RequestBody OrderDto orderDto) {
        return 1L;
    }

    @DeleteMapping
    public void deleteOrder(@RequestParam Long id) {

    }

    @PutMapping
    public OrderDto updateOrder(@RequestParam Long id, @RequestBody OrderDto orderDto) {
        return orderDto;
    }
}
