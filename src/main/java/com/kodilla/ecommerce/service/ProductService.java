
package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Product;

import java.util.List;



public interface ProductService {

    List<Product> getProducts();
    Product findById(final Long productId);
    void deleteProduct(final Long productId);
    Product saveProduct(final Product product);
    Product updateProduct(final Long productId,final Product product);
}