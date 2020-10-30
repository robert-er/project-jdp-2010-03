package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.mapper.CartMapper;
import com.kodilla.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartMapper cartMapper;
    private final CartService cartService;

    @PostMapping
    public CartDto createCart(@RequestBody CartDto cartDto) {
        return cartMapper.mapToCartDto(cartService.createCart(cartMapper.mapToCart(cartDto)));
    }

    @GetMapping("{id}")
    public List<ProductDto> getElementsFromCart(@PathVariable Long id) {
        //odkomentować po utworzeniu productMapper

     //   return productMapper.mapToProductDtoList(cartService.getElementsFromCart(id));
        return new ArrayList<>();
    }

    @PostMapping("{id}")
    public void addProductToCart(@PathVariable Long id, @RequestParam Long productId) {
        cartService.addProductToCart(id, productId);
    }

    @DeleteMapping("{id}")
    public void deleteProductFromCart(@PathVariable Long id, @RequestParam Long productId) {
        cartService.deleteProductFromCart(id, productId);
    }

    @PostMapping("createOrder/{id}")
    public void createOrderFromCart(@PathVariable Long id) {
        cartService.createOrderFromCart(id);
    }
}