package com.kodilla.ecommerce.config;

import com.kodilla.ecommerce.mapper.OrderItemMapper;
import com.kodilla.ecommerce.mapper.OrderMapper;
import com.kodilla.ecommerce.repository.*;
import com.kodilla.ecommerce.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    protected CartItemService createCartItemService(CartItemRepository cartItemRepository) {
        return new CartItemServiceImpl(cartItemRepository);
    }

    @Bean
    protected CartService createCartService(CartRepository cartRepository,
                                            ProductRepository productRepository,
                                            OrderService orderService,
                                            CartItemService cartItemService) {
        return new CartServiceImpl(cartRepository, productRepository, orderService, cartItemService);
    }

    @Bean
    protected GroupService createGroupService(GroupRepository groupRepository) {
        return new GroupServiceImpl(groupRepository);
    }

    @Bean
    protected OrderService createOrderService(OrderRepository orderRepository,
                                              ProductRepository productRepository,
                                              OrderItemMapper orderItemMapper,
                                              OrderItemRepository orderItemRepository,
                                              UserRepository userRepository,
                                              OrderMapper orderMapper) {
        return new OrderServiceImpl(orderRepository, productRepository, orderItemMapper, orderItemRepository, userRepository, orderMapper);
    }

    @Bean
    protected ProductService createProductService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    @Bean
    protected UserService createUserService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    protected HistoryService createHistoryService(HistoryRepository historyRepository) {
        return new HistoryServiceImpl(historyRepository);
    }
}
