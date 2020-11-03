package com.kodilla.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
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
    private Long quantityInStock;

    @OneToMany(mappedBy = "products")
    private List<CartItem> cartItems;

    @JsonBackReference
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="group_id")
    private Group group;

    @OneToMany(mappedBy = "products")
    private List<OrderItem> orderItems;



}
