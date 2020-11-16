package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    private final Order order = new Order();
    private final User user = new User("nick77", "name33", "surname44",
            "email88@dot.com", false);

    @Test
    public void shouldGetId() {
        //Given
        userRepository.save(user);
        order.setUser(user);
        orderRepository.save(order);
        //When
        Long orderId = order.getId();
        //Then
        assertTrue(orderRepository.findById(orderId).isPresent());
        //CleanUp
        userRepository.deleteById(user.getId());
    }

    @Test
    public void shouldGetName() {
        //Given
        String name = "Order name";
        userRepository.save(user);
        order.setUser(user);
        order.setName(name);
        orderRepository.save(order);
        //When
        String returnedName = order.getName();
        //Then
        assertEquals(name, returnedName);
        //CleanUp
        userRepository.deleteById(user.getId());
    }

    @Test
    public void shouldGetDescription() {
        //Given
        String description = "Order description";
        userRepository.save(user);
        order.setUser(user);
        order.setDescription(description);
        orderRepository.save(order);
        //When
        String returnedDescription = order.getDescription();
        //Then
        assertEquals(description, returnedDescription);
        //CleanUp
        userRepository.deleteById(user.getId());
    }

    @Test
    public void shouldGetStatus() {
        //Given
        OrderStatus status = OrderStatus.CANCELLED;
        userRepository.save(user);
        order.setUser(user);
        order.setStatus(status);
        orderRepository.save(order);
        //When
        OrderStatus returnedStatus = order.getStatus();
        //Then
        assertEquals(status, returnedStatus);
        //CleanUp
        userRepository.deleteById(user.getId());
    }

    @Test
    public void shouldGetUser() {
        //Given
        userRepository.save(user);
        order.setUser(user);
        orderRepository.save(order);
        //When
        User returnedUser = order.getUser();
        //Then
        assertEquals(user, returnedUser);
        //CleanUp
        userRepository.deleteById(user.getId());
    }

    @Test
    public void shouldGetItems() {
        //Given
        userRepository.save(user);
        order.setUser(user);
        List<OrderItem> orderItems = prepareItems();
        order.setItems(orderItems);
        orderRepository.save(order);
        //When
        List<OrderItem> returnedItems = order.getItems();
        //Then
        assertEquals(orderItems, returnedItems);
        //CleanUp
        Long groupId = order.getItems().get(0).getProduct().getGroup().getId();
        userRepository.deleteById(user.getId());
        groupRepository.deleteById(groupId);
    }

    @Test
    public void shouldDeleteUserAndOrderAndOrderItemsButRetainProductAndGroup() {
        //Given
        userRepository.save(user);
        order.setUser(user);
        List<OrderItem> orderItems = prepareItems();
        order.setItems(orderItems);
        orderRepository.save(order);
        Long productId = order.getItems().get(0).getProduct().getId();
        Long groupId = order.getItems().get(0).getProduct().getGroup().getId();
        //When
        userRepository.deleteById(user.getId());
        //Then
        assertNotNull(productRepository.findById(productId));
        assertNotNull(groupRepository.findById(groupId));
        assertEquals(Optional.empty(), userRepository.findById(user.getId()));
        assertEquals(Optional.empty(), orderItemRepository.findById(order.getId()));
        //CleanUp
        groupRepository.deleteById(groupId);
    }

    private List<OrderItem> prepareItems() {
        Group group = new Group();
        Product product1 = new Product();
        product1.setGroup(group);
        Product product2 = new Product();
        product2.setGroup(group);
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrder(order);
        orderItem1.setProduct(product1);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setOrder(order);
        orderItem2.setProduct(product2);
        return Arrays.asList(orderItem1, orderItem2);
    }
}