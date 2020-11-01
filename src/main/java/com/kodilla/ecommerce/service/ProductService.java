package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Optional <Product> getProduct(final Long productId){
        return productRepository.findById(productId);
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
}
