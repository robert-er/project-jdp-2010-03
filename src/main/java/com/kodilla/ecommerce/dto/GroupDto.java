package com.kodilla.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GroupDto {

    private long id;
    private String name;
    private String description;
    private List<ProductDto> products;
}
