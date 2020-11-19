package com.kodilla.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ORDER_ITEMS")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @NotNull
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    @JoinColumn(name = "product_id")
    private Product product;

    private Long quantity;
    private BigDecimal subtotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return order.equals(orderItem.order) &&
                product.equals(orderItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
