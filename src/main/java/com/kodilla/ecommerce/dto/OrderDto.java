package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String name;
    private String description;
    private Long userId;
    private OrderStatus status;
    private List<OrderItemDto> items;
}
