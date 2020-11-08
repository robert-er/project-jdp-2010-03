package com.kodilla.ecommerce.domain;

        import com.fasterxml.jackson.annotation.JsonBackReference;
        import com.fasterxml.jackson.annotation.JsonManagedReference;

        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import javax.persistence.*;
        import javax.validation.constraints.NotNull;
        import java.math.BigDecimal;
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

    @JsonManagedReference
    @OneToMany(targetEntity = CartItem.class,
            mappedBy = "product",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    @JsonBackReference
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="group_id")
    private Group group;

    @JsonBackReference
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;



}