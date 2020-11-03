package com.kodilla.ecommerce.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "CART_ITEMS")
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;

}
