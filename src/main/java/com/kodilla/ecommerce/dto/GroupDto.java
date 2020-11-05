package com.kodilla.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private long id;
    private String name;
    private String description;
    private List<ProductDto> products;

}
