package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.dto.CartItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemMapper {

    public CartItem mapToCartItem(CartItemDto cartItemDto) {
        CartItem item = new CartItem();
        item.setId(cartItemDto.getId());
        item.setCart(cartItemDto.getCart());
        item.setProduct(cartItemDto.getProduct());
        item.setQuantity(cartItemDto.getQuantity());
        return item;
    }

    public CartItemDto mapToCartItemDto(CartItem cartItem) {
        CartItemDto itemDto = new CartItemDto();
        itemDto.setId(cartItem.getId());
        itemDto.setCart(cartItem.getCart());
        itemDto.setProduct(cartItem.getProduct());
        itemDto.setQuantity(cartItem.getQuantity());
        return itemDto;
    }

    public List<CartItemDto> mapToCartItemDtoList(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::mapToCartItemDto)
                .collect(Collectors.toList());
    }
}
