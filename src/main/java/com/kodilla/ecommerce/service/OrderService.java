package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Optional<Order> getOrderById(final Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrders() { return orderRepository.findAll(); }

    public Order saveOrder(final Order order) { return orderRepository.save(order);}

    public void deleteById(final Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
