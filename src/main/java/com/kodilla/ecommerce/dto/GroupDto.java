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
    private List<Product> products;

    public GroupDto(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }
}
