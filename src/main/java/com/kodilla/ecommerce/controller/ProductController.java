package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.mapper.ProductMapper;
import com.kodilla.ecommerce.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {


    private final ProductMapper productMapper;
    private final ProductServiceImpl productServiceImpl;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(productServiceImpl.getProducts());
    }

    @GetMapping("{productId}")
    public ProductDto getProduct(@PathVariable Long productId) throws NotFoundException {
        return productMapper.mapToProductDto(productServiceImpl.getProduct(productId));
    }

    @DeleteMapping("{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productServiceImpl.deleteProduct(productId);
    }

    @PutMapping("{productId}")
    public ProductDto updateProduct(@PathVariable Long productId,@RequestBody ProductDto productDto) {
      return productMapper.mapToProductDto(productServiceImpl.updateProduct(productId, productMapper.mapToProduct(productDto)));
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {
        productServiceImpl.saveProduct(productMapper.mapToProduct(productDto));
    }

}