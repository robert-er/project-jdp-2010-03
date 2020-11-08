
package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.domain.OrderItem;
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
}