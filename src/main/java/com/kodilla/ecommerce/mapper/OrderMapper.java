package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.domain.OrderItem;
import com.kodilla.ecommerce.dto.*;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ProductMapper productMapper;

    public OrderDto mapToOrderDto(final Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setName(order.getName());
        orderDto.setDescription(order.getDescription());
        orderDto.setStatus(order.getStatus());
        orderDto.setUserId(order.getUser().getId());

        List<OrderItem> orderItemProducts = order.getItems();
        List<OrderItemDto> orderItems = new ArrayList<>();
        for (OrderItem product : orderItemProducts) {
            OrderItemDto orderItemDto = new OrderItemDto();

            orderItemDto.setId(product.getId());
            orderItemDto.setProduct(productMapper.mapToProductInOrderItemDto(product.getProduct()));
            orderItemDto.setQuantity(product.getQuantity());

            orderItems.add(orderItemDto);
        }
        orderDto.setItems(orderItems);

        return orderDto;
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(t -> mapToOrderDto(t))
                .collect(Collectors.toList());
    }
}
