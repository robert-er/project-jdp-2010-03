package com.kodilla.ecommerce.domain.group;

import com.kodilla.ecommerce.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private long id;
    private String name;
    private List<Product> products;

    public Group(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }
}
