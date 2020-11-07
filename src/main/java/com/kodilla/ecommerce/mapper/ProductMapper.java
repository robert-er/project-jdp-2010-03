package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.ProductDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product mapToProduct(final ProductDto productDto){

        Product product = new Product();
              product.setTitle(productDto.getTitle());
              product.setPrice(productDto.getPrice());
              product.setDescription(productDto.getDescription());
              product.setQuantityInStock(productDto.getQuantityInStock());
              product.setCartItems(productDto.getCartItems());
              product.setGroup(productDto.getGroup());
              product.setOrderItems(productDto.getOrderItems());

        return product;
    }

    public ProductDto mapToProductDto (final Product product){
        return new ProductDto(product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getDescription(),
                product.getQuantityInStock(),
                product.getCartItems(),
                product.getGroup(),
                product.getOrderItems());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> products) {

        return products.stream()
                .map(product -> new ProductDto(product.getId(),
                        product.getTitle(),
                        product.getPrice(),
                        product.getDescription(),
                        product.getQuantityInStock(),
                        product.getCartItems(),
                        product.getGroup(),
                        product.getOrderItems()))
                .collect(Collectors.toList());
    }
}
