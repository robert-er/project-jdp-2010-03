package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {

    @RequestMapping(method = RequestMethod.POST, value = "createCart")
    public void createCart(@RequestBody CartDto cartDto) {

    }

    @RequestMapping(method = RequestMethod.GET, value = "getElementsFromEmptyCart")
    public CartDto getElementsFromEmptyCart() {
        CartDto cartDto = new CartDto(1l);
        return cartDto;
    }

    @RequestMapping(method = RequestMethod.POST, value = "addProductsToCart")
    public void addProductsToCart(@RequestBody CartDto cartDto) {

    }


    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam Long productId) {

    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrderFromCart")
    public void createOrderFromCart(@RequestBody CartDto cartDto) {

    }

}