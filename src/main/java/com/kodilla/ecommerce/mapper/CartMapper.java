package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.CartItemRepository;
import com.kodilla.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public Cart mapToCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setUser(userRepository.findById(cartDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User id: " + cartDto.getUserId() + " not found")));
        List<CartItem> items = cartDto.getCartIdList().stream()
                .map(itm -> cartItemRepository.findById(itm).orElse(null))
                .collect(Collectors.toList());
        cart.setItems(items);
        return cart;
    }

    public CartDto mapToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setUserId(cart.getUser().getId());
        List<Long> itemIds = cart.getItems().stream()
                .map(CartItem::getId)
                .collect(Collectors.toList());
        cartDto.setCartIdList(itemIds);
        return cartDto;
    }
}
