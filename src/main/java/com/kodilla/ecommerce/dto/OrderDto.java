package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String name;
    private String description;
    private Order.Status status;
}
