package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.domain.OrderItem;
import com.kodilla.ecommerce.dto.OrderItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {

    public List<OrderItem> mapToOrderItem(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::mapToOrderItem)
                .collect(Collectors.toList());
    }

    private OrderItem mapToOrderItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        return orderItem;
    }

    public List<OrderItem> mapToOrderItemFromDto(List<OrderItemDto> orderItemsDto) {
        return orderItemsDto.stream()
                .map(this::mapToOrderItem)
                .collect(Collectors.toList());
    }

    public List<OrderItemDto> mapToOrderItemDto(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::mapToOrderItemDto)
                .collect(Collectors.toList());
    }

    private OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProduct(orderItem.getProduct());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }

    private OrderItem mapToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(orderItemDto.getProduct());
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }
}
