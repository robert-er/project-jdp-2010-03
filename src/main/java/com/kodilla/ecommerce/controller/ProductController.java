package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts() {
       List<ProductDto> products = new ArrayList<>();
       products.add(new ProductDto(1L, "First Product", 340L, "Example description", 20L));
       products.add(new ProductDto(2L, "Second Product", 346L, "Example description", 10L));
       products.add(new ProductDto(3L, "Third Product", 345L, "Example description", 80L));
       products.add(new ProductDto(4L, "Fourth Product", 370L, "Example description", 30L));
       return products;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProduct(@RequestParam Long productId) {
        return new ProductDto(1L, "Product title", 123L, "Product description", 23L);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(2L, "Product title - Edit", 123L, "Product description - Edit", 234L);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {

    }

}

