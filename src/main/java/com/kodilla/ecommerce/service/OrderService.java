package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;

public interface OrderService {

    void createOrder(Cart cart);
}
