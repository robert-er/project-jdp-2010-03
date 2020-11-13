package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.*;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.mapper.OrderItemMapper;
import com.kodilla.ecommerce.repository.OrderItemRepository;
import com.kodilla.ecommerce.repository.OrderRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Order getOrderById(final Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order id: " + orderId +
                        " not found in Order database"));
    }

    @Override
    public List<Order> getAllOrders() { return orderRepository.findAll(); }

    @Override
    public Order saveOrder(final Order order) { return orderRepository.save(order);}

    @Override
    public Order updateOrderById(final Long orderId, Order order) throws NotFoundException {
        Order foundOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order id: " + orderId +
                " not found in Order database"));
        foundOrder.setName(order.getName());
        foundOrder.setDescription(order.getDescription());
        foundOrder.setUser(order.getUser());
        foundOrder.setStatus(order.getStatus());
        foundOrder.setItems(order.getItems());

        return orderRepository.save(foundOrder);
    }

    @Override
    public void deleteById(final Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new NotFoundException("Order id: " + orderId + " not found in Order database");
        }
    }

    @Override
    public boolean createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStatus(OrderStatus.CONFIRMED);
        List<OrderItem> orderItems = orderItemMapper.mapToOrderItem(cart.getItems());
        for(OrderItem item : orderItems) {
            if (isEnoughProductQuantityInDB(item.getProduct(), item.getQuantity())) {
                addOrderItemToOrder(order, item);
                changeProductQuantityInDB(item.getProduct(), item.getQuantity());
            }
        }
        return true;
    }

    private void addOrderItemToOrder(Order order, OrderItem item) {
        item.setOrder(order);
        orderItemRepository.save(item);
        saveOrder(order);
    }

    private boolean isEnoughProductQuantityInDB(Product product, Long quantity) throws NotFoundException {
        Long quantityInStock = productRepository.findById(product.getId())
                .orElseThrow(() -> new NotFoundException("Product id: " + product.getId()
                        + " not found in Product database"))
                .getQuantityInStock();
        if(quantityInStock < quantity) {
            throw new NotFoundException("Not enough quantity (" + quantity + ") of product id: " + product.getId());
        } else {
            return true;
        }
    }

    private void changeProductQuantityInDB(Product product, Long quantity) {
        product.setQuantityInStock(product.getQuantityInStock() - quantity);
        productRepository.save(product);
    }
}
