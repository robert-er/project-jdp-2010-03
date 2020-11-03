package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductMapper {

    public Map<Long, ProductDto> mapToProductDtoList(Map<Long, Product> products) {
        //potrzebna implementacja
        return new HashMap<>();
    }
}
