package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

    private final ProductMapper productMapper;

    public CartItemDto mapToCartItemDto(CartItem cartItem) {
        CartItemDto itemDto = new CartItemDto();
        itemDto.setId(cartItem.getId());
        itemDto.setCartId(cartItem.getCart().getId());
        itemDto.setProduct(productMapper.mapToProductInCartItemDto(cartItem.getProduct()));
        itemDto.setQuantity(cartItem.getQuantity());
        return itemDto;
    }

    public List<CartItemDto> mapToCartItemDtoList(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::mapToCartItemDto)
                .collect(Collectors.toList());
    }
}