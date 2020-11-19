package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.*;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.exception.NotValidException;
import com.kodilla.ecommerce.mapper.OrderItemMapper;
import com.kodilla.ecommerce.mapper.OrderMapper;
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
    private final OrderMapper orderMapper;

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
    public Order updateOrderById(final Long orderId, final OrderDto orderDto) throws NotFoundException {
        Order foundOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order id: " + orderId +
                        " not found in Order database"));
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User id: " + orderDto.getUserId() + " not found"));

        foundOrder.setName(orderDto.getName());
        foundOrder.setDescription(orderDto.getDescription());
        foundOrder.setUser(user);
        foundOrder.setStatus(orderDto.getStatus());

        List<OrderItem> foundOrderList = foundOrder.getItems();
        int listSize = foundOrderList.size();
        for(int n = listSize - 1; n >= 0; n--) {
            Long itemIdToRemove = foundOrderList.get(n).getId();
            foundOrderList.remove(n);
            orderItemRepository.deleteById(itemIdToRemove);
        }

        if (orderDto.getItems() != null) {
            return addProductsFromOrderDto(orderDto, foundOrder);
        } else {
            saveOrder(foundOrder);
            return foundOrder;
        }
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
    public Order createOrderWithoutCart(OrderDto orderDto) throws NotFoundException {
        Order order = orderMapper.mapOrderDtoWithoutItems(orderDto);
        if (orderDto.getItems() != null) {
            return addProductsFromOrderDto(orderDto, order);
        } else {
            saveOrder(order);
            return order;
        }
    }

    private Order addProductsFromOrderDto(OrderDto orderDto, Order order) {
        for(int n=0; n < orderDto.getItems().size(); n++) {
            OrderItem item = orderItemMapper.mapOrderItemDtoToOrderItem(orderDto.getItems().get(n));
            Long productId = item.getProduct().getId();
            Long quantity = item.getQuantity();
            validateQuantity(item.getQuantity());
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product id: " + productId + " not found in Product database"));

            item.setProduct(product);
            item.setQuantity(quantity);

            if (isEnoughProductQuantityInDB(item.getProduct(), item.getQuantity())) {
                addOrderItemToOrder(order, item);
                changeProductQuantityInDB(item.getProduct(), item.getQuantity());
            }
        }
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
