package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.Product;
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

    public GroupDto(String name, String description, List<ProductDto> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }

}
