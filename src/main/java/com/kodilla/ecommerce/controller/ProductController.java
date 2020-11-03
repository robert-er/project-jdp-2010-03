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

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(productService.getProducts());
    }


    @RequestMapping(method = RequestMethod.GET, value = "getProduct/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) throws NotFoundException {
        return productMapper.mapToProductDto(productService.getProduct(productId).orElseThrow(NotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
      return productMapper.mapToProductDto(productService.saveProduct
              (productMapper.mapToProduct(productDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }

}