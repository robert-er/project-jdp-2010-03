package com.kodilla.ecommerce.config;

import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.repository.OrderRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import com.kodilla.ecommerce.service.CartService;
import com.kodilla.ecommerce.service.CartServiceImpl;
import com.kodilla.ecommerce.service.OrderService;
import com.kodilla.ecommerce.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    protected CartService createCartService(CartRepository cartRepository, ProductRepository productRepository,
                                            OrderRepository orderRepository, OrderService orderService) {
        return new CartServiceImpl(cartRepository, productRepository, orderRepository, orderService);
    }

    @Bean
    protected OrderService createOrderService(OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }
}
