package com.kodilla.ecommerce.config;

import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.service.CartService;
import com.kodilla.ecommerce.service.CartServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    protected CartService createCartService(CartRepository cartRepository){
        return new CartServiceImpl(cartRepository);
    }
}
