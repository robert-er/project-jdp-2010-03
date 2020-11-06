package com.kodilla.ecommerce.config;

import com.kodilla.ecommerce.mapper.OrderItemMapper;
import com.kodilla.ecommerce.repository.*;
import com.kodilla.ecommerce.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    protected CartService createCartService(CartRepository cartRepository, ProductRepository productRepository,
                                            OrderRepository orderRepository, OrderService orderService,
                                            CartItemService cartItemService) {
        return new CartServiceImpl(cartRepository, productRepository, orderRepository, orderService, cartItemService);
    }

    @Bean
    protected OrderService createOrderService(OrderRepository orderRepository,
                                              ProductRepository productRepository,
                                              OrderItemMapper orderItemMapper,
                                              OrderItemRepository orderItemRepository) {
        return new OrderServiceImpl(orderRepository, productRepository, orderItemMapper, orderItemRepository);
    }

    @Bean
    protected CartItemService createCartItemService(CartItemRepository cartItemRepository) {
        return new CartItemServiceImpl(cartItemRepository);
    }

    @Bean
    protected ProductService createProductService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }
}
