package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;

public interface OrderService {

    boolean createOrder(Cart cart);
}
