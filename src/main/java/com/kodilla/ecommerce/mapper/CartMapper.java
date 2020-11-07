package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.dto.CartItemDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final UserRepository userRepository;
    private final CartItemMapper cartItemMapper;

    public Cart mapToCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setUser(userRepository.findById(cartDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User id: " + cartDto.getUserId() + " not found")));
        cart.setItems(new ArrayList<>());
        return cart;
    }

    public CartDto mapToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setUserId(cart.getUser().getId());
        List<CartItemDto> items = cartItemMapper.mapToCartItemDtoList(cart.getItems());
        cartDto.setItems(items);
        return cartDto;
    }
}
