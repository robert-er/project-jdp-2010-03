package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.*;
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
    private Long quantityInStock;
    private List<CartItem> cartItems;
    private Group group;
    private List<OrderItem> orderItems;

    public ProductDto(String title, BigDecimal price, String description, Long quantityInStock, Group group) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.quantityInStock = quantityInStock;
        this.group = group;
    }
}
