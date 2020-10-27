package com.kodilla.ecommerce.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "AMOUNT")
    private Integer amount;

    //trzeba odkomentować po dodaniu encji Cart
    /*@ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;*/

    //trzeba odkomentować po dodaniu encji Group
    /*@ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;*/

    //trzeba odkomentować po dodaniu encji Order
    /*@ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;*/

    public Product(String name, Double price, Integer amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}