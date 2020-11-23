package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.domain.OrderItem;
import com.kodilla.ecommerce.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

    private final ProductMapper productMapper;

    public List<OrderItem> mapToOrderItems(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::mapToOrderItem)
                .collect(Collectors.toList());
    }

    private OrderItem mapToOrderItem(CartItem cartItem) {
        return OrderItem.builder()
                .product(cartItem.getProduct())
                .quantity(cartItem.getQuantity())
                .build();
    }

    public OrderItem mapOrderItemDtoToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productMapper.mapProductInOrderItemDtoToProduct(orderItemDto.getProduct()));
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }
}
