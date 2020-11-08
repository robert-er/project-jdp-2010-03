package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.exception.NotFoundException;
import com.kodilla.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



public interface ProductService {



    List<Product> getProducts();
    Product getProduct(final Long productId);
    void deleteProduct(final Long productId);
    Product saveProduct(final Product product);
    Product updateProduct(final Long productId,final Product product);
}
