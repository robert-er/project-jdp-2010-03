package com.kodilla.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInCartItemDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private GroupInCartItemDto group;
}
