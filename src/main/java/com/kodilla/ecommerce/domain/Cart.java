package com.kodilla.ecommerce.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "CART_PRODUCT",
            joinColumns = { @JoinColumn(name = "cart_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    List<Product> products;
}
