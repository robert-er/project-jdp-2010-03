package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.ProductDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product mapToProduct(final ProductDto productDto){

        Product product = new Product(productDto.getTitle(),productDto.getPrice(),productDto.getDescription(),productDto.getQuantity());
        product.setId(productDto.getId());
        product.setCarts(productDto.getCarts());
        product.setGroup(productDto.getGroup());
        product.setOrders(productDto.getOrders());

        return product;
    }

    public ProductDto mapToProductDto (final Product product){
        return new ProductDto(product.getId(),product.getTitle(),product.getPrice(),product.getDescription(),product.getQuantity(),product.getCarts(),product.getGroup(),product.getOrders());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> products) {

        return products.stream()
                .map(product -> new ProductDto(product.getId(),
                        product.getTitle(),
                        product.getPrice(),
                        product.getDescription(),
                        product.getQuantity(),
                        product.getCarts(),
                        product.getGroup(),
                        product.getOrders()))
                .collect(Collectors.toList());
    }
}
