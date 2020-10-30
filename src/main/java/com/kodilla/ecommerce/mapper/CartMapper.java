package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.CartDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public Cart mapToCart(CartDto cartDto) {
        Cart cart = new Cart();

        // odkomentować po utworzeniu userRepository

    //    cart.setUser(userRepository.findById(cartDto.getUserId()));
        cart.setProducts(new ArrayList<>());
        return cart;
    }

    public CartDto mapToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setUserId(cart.getUser().getId());
        cartDto.setProductIdList(cart.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
        return cartDto;
    }

}
