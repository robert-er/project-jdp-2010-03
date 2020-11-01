package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        //potrzebna implementacja
        return new ArrayList<>();
    }
}
