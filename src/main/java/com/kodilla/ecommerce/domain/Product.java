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

    @JsonManagedReference(value = "cartItem-product")
    @OneToMany(targetEntity = CartItem.class,
            mappedBy = "product",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    @JsonBackReference(value = "product-group")
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="group_id")
    private Group group;

    @JsonManagedReference(value = "orderItem-product")
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    public Product(String title, BigDecimal price, String description, Long quantityInStock){
        this.title = title;
        this.price = price;
        this.description = description;
        this.quantityInStock = quantityInStock;
    }

}