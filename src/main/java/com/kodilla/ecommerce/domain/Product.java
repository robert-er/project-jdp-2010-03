package com.kodilla.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private Long quantity;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;

    @JsonBackReference
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="group_id")
    private Group group;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    public Product(String title, BigDecimal price, String description, Long quantity) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
}