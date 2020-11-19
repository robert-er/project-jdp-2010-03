package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.exception.NotFoundException;

import java.util.List;

public interface OrderService {

    Order getOrderById(final Long orderId);
    List<Order> getAllOrders();
    Order saveOrder(final Order order);
    Order updateOrderById( final Long orderId, final OrderDto orderDto) throws NotFoundException;
    void deleteById(final Long orderId);
    boolean createOrder(Cart cart);
    Order createOrderWithoutCart(final OrderDto orderDto) throws NotFoundException;
}
