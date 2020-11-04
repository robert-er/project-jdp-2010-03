package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.domain.Product;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {
    private Long id;
    private Order order;
    private Product product;
    private Long quantity;
    private BigDecimal subtotal;
}
