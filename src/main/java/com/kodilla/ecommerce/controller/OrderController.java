package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.mapper.OrderMapper;
import com.kodilla.ecommerce.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;
    private final OrderMapper orderMapper;

    @GetMapping("get")
    public OrderDto getOrder(@RequestParam Long id) {
        return new OrderDto(id);
    }

    @GetMapping("getAll")
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orders = new ArrayList<>();
        orders.add(new OrderDto(1L));
        orders.add(new OrderDto(2L));
        return orders;
    }

    @PostMapping("add")
    public Long addOrder(@RequestBody OrderDto orderDto) {
        return orderDto.getId();
    }

    @DeleteMapping("delete")
    public void deleteOrder(@RequestParam Long id) {

    }

    @PutMapping("update")
    public OrderDto updateOrder(@RequestParam Long id, @RequestBody OrderDto orderDto) {
        return orderDto;
    }
}
