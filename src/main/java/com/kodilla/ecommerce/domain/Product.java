package com.kodilla.ecommerce.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long price;
    private String description;
    private Long quantity;

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

    public Product(String title, Long price, String description, Long quantity) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
}