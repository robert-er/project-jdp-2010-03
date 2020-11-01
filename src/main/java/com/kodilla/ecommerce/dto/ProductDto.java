package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private Long quantity;
    private List<Cart> carts;
    private Group group;
    private List<Order> orders;
}
