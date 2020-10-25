package com.kodilla.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;

    @JsonBackReference
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="group_id")
    private Group group;
}
