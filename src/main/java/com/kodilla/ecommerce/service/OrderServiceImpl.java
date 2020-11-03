package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void createOrder(Cart cart) {
        Order order = new Order();
      //  order.setProducts(cart.getProducts());
        order.setUser(cart.getUser());
        order.setStatus(Order.Status.CONFIRMED);
        saveOrder(order);
    }
}
