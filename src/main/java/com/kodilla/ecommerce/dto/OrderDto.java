package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.OrderStatus;
import com.kodilla.ecommerce.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String name;
    private String description;
    private OrderStatus status;
}
