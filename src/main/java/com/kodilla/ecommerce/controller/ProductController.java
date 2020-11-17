package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.HistoryType;
import com.kodilla.ecommerce.dto.HistoryEntryDto;
import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.mapper.HistoryEntryMapper;
import com.kodilla.ecommerce.mapper.ProductMapper;
import com.kodilla.ecommerce.service.HistoryService;
import com.kodilla.ecommerce.service.ProductService;
import com.kodilla.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    private final UserService userService;
    private final HistoryService historyService;
    private final HistoryEntryMapper historyEntryMapper;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(productService.getProducts());
    }

    @GetMapping("{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        return productMapper.mapToProductDto(productService.findById(productId));
    }

    @DeleteMapping("{productId}")
    public void deleteProduct(@PathVariable Long productId,
                              @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        productService.deleteProduct(productId);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.PRODUCT, "deleteProduct");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
    }

    @PutMapping("{productId}")
    public ProductDto updateProduct(@PathVariable Long productId,@RequestBody ProductDto productDto,
                                    @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.PRODUCT, "updateProduct");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
        return productMapper
                .mapToProductDto(productService.updateProduct(productId, productMapper.mapToProduct(productDto)));
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto,
                              @RequestParam Long userId, @RequestParam String key) {
        userService.validateGeneratedKey(userId, key);
        productService.saveProduct(productMapper.mapToProduct(productDto));
        HistoryEntryDto historyEntryDto = new HistoryEntryDto(LocalDateTime.now(),
                HistoryType.PRODUCT, "createProduct");
        historyService.addEntryToHistory(userId, historyEntryMapper.mapToHistoryEntry(historyEntryDto, userId));
    }
}
