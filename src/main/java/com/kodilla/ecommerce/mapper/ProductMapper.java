package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.dto.ProductInCartItemDto;
import com.kodilla.ecommerce.dto.ProductInOrderItemDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.GroupRepository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    public ProductMapper(@Lazy GroupMapper groupMapper,@Lazy GroupRepository groupRepository) {
        this.groupMapper = groupMapper;
        this.groupRepository = groupRepository;
    }

    public Product mapToProduct(final ProductDto productDto){

        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setQuantityInStock(productDto.getQuantityInStock());
        product.setGroup(groupRepository.findById(productDto.getGroupId()).orElseThrow(() -> new NotFoundException("Group id: " + productDto.getGroupId() + " not found")));

        return product;
    }
    public ProductDto mapToProductDto (final Product product){
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getDescription(),
                product.getQuantityInStock(),
                product.getGroup().getId());
    }
    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getTitle(),
                        product.getPrice(),
                        product.getDescription(),
                        product.getQuantityInStock()
                        ,product.getGroup().getId()))
                .collect(Collectors.toList());
    }
    public ProductInCartItemDto mapToProductInCartItemDto(Product product) {
        ProductInCartItemDto productInCartItemDto = new ProductInCartItemDto();
        productInCartItemDto.setId(product.getId());
        productInCartItemDto.setTitle(product.getTitle());
        productInCartItemDto.setPrice(product.getPrice());
        productInCartItemDto.setDescription(product.getDescription());
        productInCartItemDto.setGroup(groupMapper.mapToGroupInCartDto(product.getGroup()));
        return productInCartItemDto;
    }

    public ProductInOrderItemDto mapToProductInOrderItemDto(Product product) {
        ProductInOrderItemDto productInOrderItemDto = new ProductInOrderItemDto();
        productInOrderItemDto.setId(product.getId());
        productInOrderItemDto.setTitle(product.getTitle());
        productInOrderItemDto.setPrice(product.getPrice());
        productInOrderItemDto.setDescription(product.getDescription());
        productInOrderItemDto.setGroup(groupMapper.mapToGroupInOrderDto(product.getGroup()));
        return productInOrderItemDto;
    }

    public Product mapProductInOrderItemDtoToProduct(ProductInOrderItemDto productInOrderItemDto) {
        Product product = new Product();
        product.setId(productInOrderItemDto.getId());
        product.setTitle(productInOrderItemDto.getTitle());
        product.setPrice(productInOrderItemDto.getPrice());
        product.setDescription(productInOrderItemDto.getDescription());
        product.setGroup(groupMapper.mapGroupInOrderItemDtotoGroup(productInOrderItemDto.getGroup()));
        return product;
    }

}
