package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.*;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.exception.NotValidException;
import com.kodilla.ecommerce.mapper.OrderItemMapper;
import com.kodilla.ecommerce.mapper.ProductMapper;
import com.kodilla.ecommerce.repository.OrderItemRepository;
import com.kodilla.ecommerce.repository.OrderRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import com.kodilla.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

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
    public Order updateOrderById(final Long orderId, final OrderDto orderDto, Long productId, Long quantity) throws NotFoundException {
        Order foundOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order id: " + orderId +
                " not found in Order database"));
        validateQuantity(quantity);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"));
        Long quantityInStock = product.getQuantityInStock();
        if(quantityInStock < quantity) {
            throw new NotFoundException("Not enough quantity (" + quantity + ") of product id: " + productId);
        }
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User id: " + orderDto.getUserId() + " not found"));

        foundOrder.setName(orderDto.getName());
        foundOrder.setDescription(orderDto.getDescription());
        foundOrder.setUser(user);
        foundOrder.setStatus(orderDto.getStatus());

        OrderItem item = foundOrder.getItems().get(0);
        item.setProduct(product);
        item.setQuantity(quantity);
        addOrderItemToOrder(foundOrder, item);
        item.setOrder(foundOrder);

        return foundOrder;
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
        order.setName("order name");
        order.setDescription("order description, cart id: " + cart.getId());
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

    @Override
    public Order createOrderWithoutCart(OrderDto orderDto, Long productId, Long quantity) throws NotFoundException {
        validateQuantity(quantity);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"));
        Long quantityInStock = product.getQuantityInStock();
        if(quantityInStock < quantity) {
            throw new NotFoundException("Not enough quantity (" + quantity + ") of product id: " + productId);
        }
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User id: " + orderDto.getUserId() + " not found"));
        Order order = new Order();
        order.setUser(user);
        order.setName(orderDto.getName());
        order.setDescription(orderDto.getDescription());
        order.setStatus(orderDto.getStatus());
        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        addOrderItemToOrder(order, item);

        return order;
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

    private void validateQuantity(Long quantity) throws NotValidException {
        if(quantity <= 0L) {
            throw new NotValidException("Requested quantity " + quantity + " can not be lower or equal 0");
        }
    }
}
