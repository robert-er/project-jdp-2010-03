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

    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
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
                .getQuantity();
        if(quantityInStock < quantity) {
            throw new NotFoundException("Not enough quantity (" + quantity + ") of product id: " + product.getId());
        } else {
            return true;
        }
    }

    private void changeProductQuantityInDB(Product product, Long quantity) {
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
}
