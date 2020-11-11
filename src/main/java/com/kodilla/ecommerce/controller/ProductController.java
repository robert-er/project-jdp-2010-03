package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ProductDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts() {
       List<ProductDto> products = new ArrayList<>();
       products.add(new ProductDto(1L, "First Product", new BigDecimal(325), "Example description", 20L));
       products.add(new ProductDto(2L, "Second Product", new BigDecimal(314), "Example description", 10L));
       products.add(new ProductDto(3L, "Third Product", new BigDecimal(344),"Example description", 80L));
       products.add(new ProductDto(4L, "Fourth Product", new BigDecimal(354), "Example description", 30L));
       return products;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProduct(Long productId) {
        return new ProductDto(1L, "Product title", new BigDecimal(564), "Product description", 23L);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(Long productId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct(ProductDto productDto) {
        return new ProductDto(2L, "Product title - Edit", new BigDecimal(231), "Product description - Edit", 234L);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public void createProduct(ProductDto productDto) {

    }

}