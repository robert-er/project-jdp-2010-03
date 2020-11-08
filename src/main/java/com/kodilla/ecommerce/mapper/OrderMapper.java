package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.dto.OrderDto;
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

    public Order mapToOrder(final OrderDto orderDto) {
        Order order = new Order();
        order.setId(null);
        order.setUser(userRepository.findById(orderDto.getUserId()).
                orElseThrow(() -> new NotFoundException("User id: " + orderDto.getUserId() + " not found")));
        order.setItems(new ArrayList<>());
        order.setName(orderDto.getName());
        order.setDescription(orderDto.getDescription());
        order.setStatus(orderDto.getStatus());
        return order;
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getName(),
                order.getDescription(),
                order.getUser().getId(),
                order.getStatus()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(t -> mapToOrderDto(t))
                .collect(Collectors.toList());
    }
}
