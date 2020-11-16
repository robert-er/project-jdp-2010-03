package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.exception.ProductAlreadyExistException;
import com.kodilla.ecommerce.mapper.ProductMapper;
import com.kodilla.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(productService.getProducts());
    }

    @GetMapping("{productId}")
    public ProductDto getProduct(@PathVariable Long productId) throws NotFoundException {
        return productMapper.mapToProductDto(productService.findById(productId));
    }

    @DeleteMapping("{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping("{productId}")
    public ProductDto updateProduct(@PathVariable Long productId,@RequestBody ProductDto productDto) {
        return productMapper.mapToProductDto(productService.updateProduct(productId, productMapper.mapToProduct(productDto)));
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto)  {
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }
}
