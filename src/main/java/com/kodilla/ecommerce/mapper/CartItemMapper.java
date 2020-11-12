package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.dto.CartItemDto;
import com.kodilla.ecommerce.service.CartService;
import com.kodilla.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

    private final CartService cartService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public CartItem mapToCartItem(CartItemDto cartItemDto) {
        CartItem item = new CartItem();
        item.setId(cartItemDto.getId());
        item.setCart(cartService.findById(cartItemDto.getCartId()));
        item.setProduct(productService.findById(cartItemDto.getProduct().getId()));
        item.setQuantity(cartItemDto.getQuantity());
        return item;
    }

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