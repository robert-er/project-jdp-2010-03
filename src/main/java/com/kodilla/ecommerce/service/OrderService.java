package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(final Long orderId);
    List<Order> getAllOrders();
    Order saveOrder(final Order order);
    Order updateOrderById(final Long orderId, Order order);
    void deleteById(final Long orderId);
    boolean createOrder(Cart cart);
}
