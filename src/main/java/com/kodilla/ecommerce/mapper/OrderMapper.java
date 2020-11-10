package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.dto.CartItemDto;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.dto.OrderItemDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final UserRepository userRepository;
    private final OrderItemMapper orderItemMapper;

    public Order mapToOrder(final OrderDto orderDto) {
        Order order = new Order();
        order.setId(null);
        order.setName(orderDto.getName());
        order.setDescription(orderDto.getDescription());
        order.setStatus(orderDto.getStatus());
        order.setUser(userRepository.findById(orderDto.getUserId()).
                orElseThrow(() -> new NotFoundException("User id: " + orderDto.getUserId() + " not found")));
        if (orderDto.getItems() != null) {
            order.setItems(orderItemMapper.mapToOrderItemFromDto(orderDto.getItems()));
        }

        return order;
    }

    public OrderDto mapToOrderDto(final Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setName(order.getName());
        orderDto.setDescription(order.getDescription());
        orderDto.setStatus(order.getStatus());
        orderDto.setUserId(order.getUser().getId());
        if (order.getItems() != null) {
            orderDto.setItems(orderItemMapper.mapToOrderItemDto(order.getItems()));
        }
        return orderDto;
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(t -> mapToOrderDto(t))
                .collect(Collectors.toList());
    }
}
