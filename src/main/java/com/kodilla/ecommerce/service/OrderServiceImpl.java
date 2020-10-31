package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Order;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order createOrder(Cart cart) {
        Order order = new Order();
        order.setProducts(cart.getProducts());
        order.setUser(cart.getUser());
        order.setStatus(Order.Status.CONFIRMED);
        return order;
    }
}
