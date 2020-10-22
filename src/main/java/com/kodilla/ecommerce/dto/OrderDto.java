package com.kodilla.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String name;
    private String description;

}
