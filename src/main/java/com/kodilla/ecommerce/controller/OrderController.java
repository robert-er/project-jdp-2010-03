package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.mapper.OrderMapper;
import com.kodilla.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

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
                                 @RequestParam Long productId, @RequestParam Long quantity) {
        orderService.createOrderWithoutCart(orderDto, productId, quantity);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
    }

    @PutMapping("{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto,
                         @RequestParam Long productId, @RequestParam Long quantity) {
        orderService.updateOrderById(id, orderDto, productId, quantity);
    }
}
