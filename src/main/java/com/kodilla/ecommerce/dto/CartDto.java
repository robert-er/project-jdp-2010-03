package com.kodilla.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {

    private Long id;
    private Long userId;
    private List<CartItemDto> items;

    public CartDto(Long userId) {
        this.userId = userId;
    }
}