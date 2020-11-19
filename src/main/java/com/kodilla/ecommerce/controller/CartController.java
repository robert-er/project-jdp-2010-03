package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.HistoryType;
import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.dto.CartItemDto;
import com.kodilla.ecommerce.dto.HistoryEntryDto;
import com.kodilla.ecommerce.mapper.CartItemMapper;
import com.kodilla.ecommerce.mapper.CartMapper;
import com.kodilla.ecommerce.mapper.HistoryEntryMapper;
import com.kodilla.ecommerce.service.CartService;
import com.kodilla.ecommerce.service.HistoryService;
import com.kodilla.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartMapper cartMapper;
    private final CartService cartService;
    private final CartItemMapper cartItemMapper;
    private final UserService userService;
    private final HistoryService historyService;
    private final HistoryEntryMapper historyEntryMapper;

    @PostMapping
    public CartDto createCart(@RequestBody CartDto cartDto,
                              @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.CART, "createCart");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
        return cartMapper.mapToCartDto(cartService.createCart(cartMapper.mapToCart(cartDto)));
    }

    @GetMapping("{id}")
    public List<CartItemDto> getElementsFromCart(@PathVariable Long id) {
        return cartItemMapper.mapToCartItemDtoList(cartService.getElementsFromCart(id));
    }

    @PostMapping("{id}")
    public void addProductToCart(@PathVariable Long id,
                                 @RequestParam Long productId, @RequestParam Long quantity,
                                 @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        cartService.increaseProductQuantityInCart(id, productId, quantity);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.CART, "addProductToCart");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
    }

    @DeleteMapping("{id}")
    public void deleteProductFromCart(@PathVariable Long id,
                                      @RequestParam Long productId, @RequestParam Long quantity,
                                      @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        cartService.decreaseProductQuantityInCart(id, productId, quantity);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.CART, "deleteProductFromCart");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
    }

    @PostMapping("createOrder/{id}")
    public void createOrderFromCart(@PathVariable Long id,
                                    @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        cartService.createOrderFromCart(id);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.CART, "createOrderFromCart");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
    }
}