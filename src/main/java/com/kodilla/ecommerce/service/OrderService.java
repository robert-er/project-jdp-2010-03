package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Order;

public interface OrderService {

    Order createOrder(Cart cart);
}
