package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Product;

public interface ProductService {

    Product findById(Long id);
}
