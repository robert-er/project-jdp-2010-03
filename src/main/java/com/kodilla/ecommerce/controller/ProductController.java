package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.mapper.ProductMapper;
import com.kodilla.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(productService.getProducts());
    }

    @GetMapping("{productId}")
    public ProductDto getProduct(@PathVariable Long productId) throws NotFoundException {
        return productMapper.mapToProductDto(productService.getProduct(productId).orElseThrow(NotFoundException::new));
    }

    @DeleteMapping("{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
      return productMapper.mapToProductDto(productService.saveProduct
              (productMapper.mapToProduct(productDto)));
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }

}