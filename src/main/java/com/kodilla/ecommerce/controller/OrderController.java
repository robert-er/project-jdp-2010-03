package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.mapper.OrderMapper;
import com.kodilla.ecommerce.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderServiceImpl orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id) throws NotFoundException {
        return orderMapper.mapToOrderDto(orderService.getOrderById(id)
                .orElseThrow(() -> new NotFoundException("Order id: " + id +
                " not found in Order database")));
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
    public void deleteOrder(@PathVariable Long id) throws NotFoundException {
        orderService.getOrderById(id).orElseThrow(() -> new NotFoundException("Order id: " + id +
                " not found in Order database"));
        orderService.deleteById(id);
    }

    @PutMapping("{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) throws NotFoundException {
        Order orderToUpdate = orderMapper.mapToOrder(orderDto);
        Order updatedOrder = orderService.updateOrderById(id, orderToUpdate);
        return orderMapper.mapToOrderDto(updatedOrder);
    }
}
