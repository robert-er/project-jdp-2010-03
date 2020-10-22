package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private long id;
    private String name;
    private List<Product> products;
}
