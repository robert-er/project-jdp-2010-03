package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.mapper.ProductMapper;
import com.kodilla.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProduct(Long productId) throws Exception {
        return productMapper.mapToProductDto(productService.getProduct(productId).orElseThrow(Exception::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(Long productId) {
        productService.deleteProduct(productId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct(ProductDto productDto) {
      return productMapper.mapToProductDto(productService.saveProduct
              (productMapper.mapToProduct(productDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public void createProduct(ProductDto productDto) {
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }

}