package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Order;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order createOrder(Cart cart) {
        return new Order();
    }
}
