package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.ProductInCartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final GroupMapper groupMapper;

    public ProductInCartItemDto mapToProductInCartItemDto(Product product) {
        ProductInCartItemDto productInCartItemDto = new ProductInCartItemDto();
        productInCartItemDto.setId(product.getId());
        productInCartItemDto.setTitle(product.getTitle());
        productInCartItemDto.setPrice(product.getPrice());
        productInCartItemDto.setDescription(product.getDescription());
        productInCartItemDto.setGroup(groupMapper.mapToGroupInCartDto(product.getGroup()));
        return productInCartItemDto;
    }
}
