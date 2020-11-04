package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.OrderItem;
import com.kodilla.ecommerce.dto.OrderItemDto;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItem mapToOrderItem(final OrderItemDto orderItemDto){
        return new OrderItem(orderItemDto.getId(),
                orderItemDto.getOrder(),
                orderItemDto.getProduct(),
                orderItemDto.getQuantity(),
                orderItemDto.getSubtotal());
    }

    public OrderItemDto mapToOrderItemDto(final OrderItem orderItem){
        return new OrderItemDto(orderItem.getId(),orderItem.getOrder(),orderItem.getProduct(),orderItem.getQuantity(),orderItem.getSubtotal());
    }
}
