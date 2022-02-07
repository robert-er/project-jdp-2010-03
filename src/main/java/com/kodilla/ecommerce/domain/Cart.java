package com.kodilla.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(targetEntity = CartItem.class,
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<CartItem> items = new ArrayList<>();

    @Builder
    public Cart(User user) {
        this.user = user;
    }
}
