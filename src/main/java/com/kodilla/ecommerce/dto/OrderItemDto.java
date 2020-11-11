package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long cartId;
    private Product product;
    private Long quantity;
}
